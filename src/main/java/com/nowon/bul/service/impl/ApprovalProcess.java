package com.nowon.bul.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nowon.bul.domain.dto.approval.AppResponseFilesDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitListDTO;
import com.nowon.bul.domain.entity.approval.Approval;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.approval.ApprovalDocRepository;
import com.nowon.bul.domain.entity.approval.ApprovalFileRepository;
import com.nowon.bul.domain.entity.approval.ApprovalFiles;
import com.nowon.bul.domain.entity.approval.ApprovalRepository;
import com.nowon.bul.domain.entity.approval.Result;
import com.nowon.bul.domain.entity.approval.State;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.service.ApprovalService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApprovalProcess implements ApprovalService {

	private final ApprovalRepository approvalRepo;

	private final ApprovalDocRepository approvalDocRepo;

	private final MemberRepository memberRepo;
	
	private final ApprovalFileRepository approvalFilesRepo;

	// 결재 상신
	@Transactional
	@Override
	public void saveApproval(ApprovalDTO dto, Member member, List<String> files) {
		ApprovalDoc approvalDoc = ApprovalDoc.builder().member(member).title(dto.getTitle()).content(dto.getContent())
				.state(State.UnderReview).build();
		approvalDocRepo.save(approvalDoc);
		approvalDoc.setDocName();

		for (int i = 1; i < dto.getLine().length + 1; i++) {
			Approval approval = Approval.builder().order(i).result(i == 1 ? Result.UnderReview : Result.Wait) // 0 : 대기중
					.receivedDate(i == 1 ? LocalDateTime.now() : null)
					.member(memberRepo.findById(dto.getLine()[i - 1]).orElseThrow()).apDoc(approvalDoc).build();
			approvalRepo.save(approval);
		}
		
		if(files != null) {
			for(String file : files) {
				ApprovalFiles  approvalFiles= ApprovalFiles.builder().fileUrl(file).approvalDoc(approvalDoc).build();  
				approvalFilesRepo.save(approvalFiles);
			}
		}
		
	}
	@Override
	public ApprovalDoc getDocByid(Long docNo) {
		return approvalDocRepo.findById(docNo).orElseThrow();
	}

	// 기안문서함 상세
	@Transactional
	@Override
	public ApprovalDraftDTO getDraft(Long docNo) {
		return approvalDocRepo.findById(docNo).orElseThrow().toApprovalDraftDTO();
	}

	// 기안문서함 리스트
	@Override
	public List<ApprovalDraftListDTO> getDraftList(Member member) {
		return approvalDocRepo.findAllByMember(member).stream().map(ApprovalDoc::toDraftListDTO)
				.collect(Collectors.toList());
	}

	// 결재대기함 상세
	@Transactional
	@Override
	public ApprovalWaitDTO getWait(Long docNo) {
		return approvalDocRepo.findById(docNo).orElseThrow().toApprovalWaitDTO();
	}

	// 결재대기함 리스트
	@Transactional
	@Override
	public List<ApprovalWaitListDTO> getWaitList(Member member) {
		Result result = Result.UnderReview;
		return approvalRepo.findAllByMemberAndResult(member, result).stream()
				.map(approval -> approval.getApDoc().toWaitListDTO()).collect(Collectors.toList());
	}

	// 결재리스트
	@Transactional
	@Override
	public List<ApprovalWaitListDTO> getApprovalList(long memberNo) {
		Member member = memberRepo.findById(memberNo).orElseThrow();
		Result result = Result.Accept;

		return approvalRepo.findAllByMemberAndResult(member, result).stream()
				.map(approval -> approval.getApDoc().toWaitListDTO()).collect(Collectors.toList());
	}


	// 문서 승인or반려
	@Transactional
	@Override
	public void changeResult(Long docno, String result, long memberNo) {
		ApprovalDoc approvalDoc = approvalDocRepo.findById(docno).orElseThrow();
		Member member = memberRepo.findById(memberNo).orElseThrow();

		Approval approval = approvalRepo.findByMemberAndApDoc(member, approvalDoc).orElseThrow();

		
		switch (result) {
		case "accept" : {
			// 다음 순서 반환
			int order = approval.accept();
			order++;
			
			// 다음 순서가 없을 시 결재문서 상태 -> 결재완료
			Approval nextApproval = approvalRepo.findByApDocAndOrder(approvalDoc, order).orElse(null);
			if (nextApproval == null) {
				System.out.println("최종결재가 완료되었습니다.");
				approvalDoc.completed();
				return;
			} else {
				nextApproval.UpdateReceivedDate();
				nextApproval.underReview();
			}
			break;
		}
		case "reject": {
			approval.reject();
			approvalDoc.reject();
			break;
		}
		default:
			throw new IllegalArgumentException("잘못된 문서 결과입니다. : " + result);
		}
	}
	
	//첨부파일 가져오기
	@Transactional
	@Override
	public List<AppResponseFilesDTO> getFiles(Long docNo) {
		ApprovalDoc approvalDoc = approvalDocRepo.findById(docNo).orElseThrow();
		return approvalFilesRepo.findAllByApprovalDoc(approvalDoc).stream()
				.map(ApprovalFiles::toAppResponseFilesDTO).collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public void saveApproval(ApprovalDTO dto, Member member, List<String> files, String[] orgNames,
			String[] bucketKeys) {
		ApprovalDoc approvalDoc = ApprovalDoc.builder().member(member).title(dto.getTitle()).content(dto.getContent())
				.state(State.UnderReview).build();
		approvalDocRepo.save(approvalDoc);
		approvalDoc.setDocName();

		for (int i = 1; i < dto.getLine().length + 1; i++) {
			Approval approval = Approval.builder().order(i).result(i == 1 ? Result.UnderReview : Result.Wait) // 0 : 대기중
					.receivedDate(i == 1 ? LocalDateTime.now() : null)
					.member(memberRepo.findById(dto.getLine()[i - 1]).orElseThrow()).apDoc(approvalDoc).build();
			approvalRepo.save(approval);
		}
		
		if(files != null) {
			for(int i=0; i<files.size(); i++) {
				ApprovalFiles  approvalFiles= ApprovalFiles.builder().fileUrl(files.get(i).substring(57))
						.approvalDoc(approvalDoc).bucketKey(bucketKeys[i]).orgName(orgNames[i]).build();  
				approvalFilesRepo.save(approvalFiles);
			}
		}
		
	}
}

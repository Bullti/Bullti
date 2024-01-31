package com.nowon.bul.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitListDTO;
import com.nowon.bul.domain.entity.approval.Approval;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.approval.ApprovalDocRepository;
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
public class ApprovalProcess implements ApprovalService{

	private final ApprovalRepository approvalRepo;
	
	private final ApprovalDocRepository approvalDocRepo;
	
	private final MemberRepository memberRepo;
	
	//결재 상신
	@Transactional
	@Override
	public void saveApproval(ApprovalDTO dto, Member member) {
		ApprovalDoc approvalDoc = ApprovalDoc.builder().member(member)
				.title(dto.getTitle())
				.content(dto.getContent())
				.state(State.UnderReview)
				.build(); 
		approvalDocRepo.save(approvalDoc);
		approvalDoc.setDocName();
		
		for(int i=1 ; i < dto.getLine().length+1; i++) {
			Approval approval = Approval.builder()
					.order(i)
					.result(i==1?Result.UnderReview : Result.Wait) // 0 : 대기중  1 : 심사중
					.receivedDate(i==1?LocalDateTime.now():null)
					.member(memberRepo.findById(dto.getLine()[i-1]).orElseThrow())
					.apDoc(approvalDoc)
					.build(); 
			approvalRepo.save(approval);
		}
	}	
	
	@Override
	public ApprovalDoc getDocByid(Long docNo) {
		return approvalDocRepo.findById(docNo).orElseThrow();
	}
	
	//기안문서함 상세
	@Transactional
	@Override
	public ApprovalDraftDTO getDraft(Long docNo) {
		return approvalDocRepo.findById(docNo).orElseThrow().toApprovalDraftDTO();
	}
	//기안문서함 리스트
	@Override
	public List<ApprovalDraftListDTO> getDraftList(Member member) {
		return approvalDocRepo.findAllByMember(member).stream()
				.map(ApprovalDoc::toDraftListDTO)
				.collect(Collectors.toList());
	}
	//결재대기함 상세
	@Transactional
	@Override
	public ApprovalWaitDTO getWait(Long docNo) {
		return approvalDocRepo.findById(docNo).orElseThrow().toApprovalWaitDTO();
	}
	
	//결재대기함 리스트
	@Transactional
	@Override
	public List<ApprovalWaitListDTO> getWaitList(Member member) {
		Result result=Result.UnderReview;
		return approvalRepo.findAllByMemberAndResult(member,result).stream()
				.map(approval -> approval.getApDoc().toWaitListDTO())
				.collect(Collectors.toList());
	}
	
}

package com.nowon.bul.service;

import java.util.List;

import com.nowon.bul.domain.dto.approval.AppRequestFilesDTO;
import com.nowon.bul.domain.dto.approval.AppResponseFilesDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitListDTO;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.utils.jpaPage.PageRequestDTO;
import com.nowon.bul.utils.jpaPage.PageResultDTO;

public interface ApprovalService {

	// 결재상신
	void saveApproval(ApprovalDTO dto, Member member, AppRequestFilesDTO files);

	List<ApprovalWaitListDTO> getWaitList(Member member);

	ApprovalDoc getDocByid(Long docNo);

	ApprovalDraftDTO getDraft(Long docNo);

	// 기안문서 리스트
	PageResultDTO<ApprovalDraftListDTO, ApprovalDoc> getDraftList(Member member, PageRequestDTO pageRequestDTO);

	ApprovalWaitDTO getWait(Long docNo);

	List<ApprovalWaitListDTO> getApprovalList(long memberNo);

	// 승인or반려
	void changeResult(Long docno, String result, long memberNo);

	// 첨부파일 가져오기
	List<AppResponseFilesDTO> getFiles(Long docNo);

}

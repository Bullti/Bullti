package com.nowon.bul.service;

import java.util.List;

import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitListDTO;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.member.Member;

public interface ApprovalService {

	//결재상신
	void saveApproval(ApprovalDTO dto, Member member);

	List<ApprovalWaitListDTO> getWaitList(Member member);

	ApprovalDoc getDocByid(Long docNo);

	ApprovalDraftDTO getDraft(Long docNo);

	List<ApprovalDraftListDTO> getDraftList(Member member);

	ApprovalWaitDTO getWait(Long docNo);

	List<ApprovalWaitListDTO> getApprovalList(long memberNo);

	//결재 승인
	void accept(Long docno, long memberNo);
	//결재 반려
	void reject(Long docno, long memberNo);

	
	
}

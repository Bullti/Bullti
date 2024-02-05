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
	void saveApproval(ApprovalDTO dto, Member member, List<String> files);

	List<ApprovalWaitListDTO> getWaitList(Member member);

	ApprovalDoc getDocByid(Long docNo);

	ApprovalDraftDTO getDraft(Long docNo);

	List<ApprovalDraftListDTO> getDraftList(Member member);

	ApprovalWaitDTO getWait(Long docNo);

	List<ApprovalWaitListDTO> getApprovalList(long memberNo);

	// 승인/반려
	void changeResult(Long docno, String result, long memberNo);

	
	
}

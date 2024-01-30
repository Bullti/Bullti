package com.nowon.bul.service;

import java.util.List;

import com.nowon.bul.domain.dto.ApprovalWaitListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.member.Member;

public interface ApprovalService {

	//결재상신
	void saveApproval(ApprovalDTO dto, Member member);

	List<ApprovalWaitListDTO> getWaitList(Member member);

	ApprovalDoc getDocByid(Long docNo);

	ApprovalWaitDTO getWait(Long docNo);

	
	
}

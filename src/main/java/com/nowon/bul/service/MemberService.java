package com.nowon.bul.service;

import java.util.List;

import com.nowon.bul.domain.dto.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;

public interface MemberService {

	void save(MemberSaveDTO dto, String profileUrl);

	//결재선 멤버 리스트
	List<ApprovalMemberListDTO> getApprovalList(String deptName);

	ApprovalMemberDTO getMemberByName(String memberName);

	
}

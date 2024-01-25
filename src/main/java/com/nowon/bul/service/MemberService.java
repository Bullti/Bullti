package com.nowon.bul.service;

import java.util.List;

import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.MemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.utils.jpaPage.PageRequestDTO;
import com.nowon.bul.utils.jpaPage.PageResultDTO;

public interface MemberService {

	void save(MemberSaveDTO dto, String profileUrl);

	//결재선 멤버 리스트
	List<ApprovalMemberListDTO> getApprovalList(String deptName);

	ApprovalMemberDTO getMemberByName(String memberName);

	//사원 조회 리스트
	PageResultDTO<MemberListDTO, Member> getFindAllList(PageRequestDTO pageRequestDTO);

	//사원번호 중복 검사
	boolean checkId(String id);

	//사원번호 유효성 검사
	boolean patternId(String id);

	
}

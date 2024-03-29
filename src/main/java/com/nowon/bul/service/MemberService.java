package com.nowon.bul.service;

import java.util.List;

import com.nowon.bul.domain.dto.IndividualDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.approval.EmpDTO;
import com.nowon.bul.domain.dto.member.MemberListDTO;
import com.nowon.bul.domain.dto.member.MemberSaveDTO;
import com.nowon.bul.domain.dto.member.MyDTO;
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

	List<ApprovalMemberListDTO> getFindById(EmpDTO dto);

	Member getFindById(long memberNo);

	IndividualDTO getIndividual(long memberNo);

	//비밀번호 변경
	void changePassword(long memberNo, String newPass);

	//현재 비밀번호 일치 검사
	boolean checkpass(long memberNo, String pass);

	//개인정보 수정
	void update(MyDTO dto, long memberNo);
	
}

package com.nowon.bul.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.department.DeRepository;
import com.nowon.bul.domain.dto.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberProcess implements MemberService{

	private final MemberRepository memberRepo;
	
	private final PasswordEncoder passEncoder;
	
	private final DeRepository deptRepo;
	
	@Override
	public void save(MemberSaveDTO dto, String profileUrl) {
		DeEntity dept = deptRepo.findById(dto.getDeptId()).orElseThrow();
		
		memberRepo.save(dto.toEntity(passEncoder, profileUrl, dept));
	}
	
	//결재선 멤버 리스트
	@Override
	public List<ApprovalMemberListDTO> getApprovalList(String DeptName) {
		System.out.println(">>>>>>>>>>>>>>>>>프로세스 실행");
		List<ApprovalMemberListDTO> list = memberRepo.findByDept_deptName(DeptName).stream()
				.map(i -> ApprovalMemberListDTO.builder().name(i.getName()).build()).collect(Collectors.toList());
		return list;
	}

	//결재문서 작성자
	@Override
	public ApprovalMemberDTO getMemberByName(String memberName) {
		System.out.println(">>>>>>>>>>>>>>>>>>Service " + memberName);
		ApprovalMemberDTO member = memberRepo.findById(memberName).orElseThrow().toApprovalMemberDTO();
		System.out.println(">>>>>>>>>>>멤버DTO>>>>>>>>>>>>> " + member.toString());
		return member;
	}




}

package com.nowon.bul.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.department.DeRepository;
import com.nowon.bul.domain.dto.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.MemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.domain.entity.member.Rank;
import com.nowon.bul.service.MemberService;
import com.nowon.bul.utils.jpaPage.PageRequestDTO;
import com.nowon.bul.utils.jpaPage.PageResultDTO;

import jakarta.transaction.Transactional;
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
		System.out.println(">>>에러를 찾아보가 다같이>>>");
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

	//사원조회 리스트
	@Transactional
	@Override
	public PageResultDTO<MemberListDTO, Member> getFindAllList(PageRequestDTO requestDTO) {
		
		Pageable pageable = requestDTO.getPageable(10, Sort.by("no").ascending());
		
		Page<Member> result = memberRepo.findAll(pageable);
		
		Function<Member, MemberListDTO> fn = entity -> entity.toListDTO();
		
		return new PageResultDTO<>(result, fn);
	}
	
	//사원번호 중복 검사
	@Override
	public boolean checkId(String id) {
		return memberRepo.existsById(id);
	}

	//사원번호 유효성 검사
	@Override
	public boolean patternId(String id) {
		return Pattern.matches("^[0-9]*$", id);
	}

	// storeManager인 멤버 조회
    public List<Member> getStoreManagers() {
        return memberRepo.findByRank(Rank.StoreManager);
    }

}

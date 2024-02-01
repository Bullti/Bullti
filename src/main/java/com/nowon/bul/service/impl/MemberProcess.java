package com.nowon.bul.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.bul.controller.EmpDTO;
import com.nowon.bul.department.DeEntity;
import com.nowon.bul.department.DeRepository;
import com.nowon.bul.domain.dto.IndividualDTO;
import com.nowon.bul.domain.dto.MemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberListDTO;
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
		List<ApprovalMemberListDTO> list = memberRepo.findByDept_deptName(DeptName).stream()
				.map(i -> ApprovalMemberListDTO.builder().name(i.getName()).rank(i.getRank().getRankName()).id(i.getId()).build()).collect(Collectors.toList());
		return list;
	}

	//결재문서 작성자
	@Transactional
	@Override
	public ApprovalMemberDTO getMemberByName(String memberName) {
		ApprovalMemberDTO member = memberRepo.findById(memberName).orElseThrow().toApprovalMemberDTO();
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

    //결재선 등록
	@Override
	public List<ApprovalMemberListDTO> getFindById(EmpDTO dto) {
		List<String> list = dto.getEmps();
		List<ApprovalMemberListDTO> result = list.stream().map(i->memberRepo.findById(i).orElse(null)) // orElse로 null 반환하도록 수정
				 .filter(Objects::nonNull) // null이 아닌 경우만 필터링		
				 .map(Member::toApprovalMemberListDTO) // DTO 변환
				 .collect(Collectors.toList());
		return result;
	}

	@Override
	public Member getFindById(long memberNo) {
		return memberRepo.findById(memberNo).orElseThrow();
	}

	//개인정보
	@Transactional
	@Override
	public IndividualDTO getIndividual(long memberNo) {
		return memberRepo.findById(memberNo).orElseThrow()
				.toIndevidualDTO();
	}

	//비밀번호 변경
	@Transactional
	@Override
	public void changePassword(long memberNo, String newPass) {
		passEncoder.encode(newPass);
		Member member = memberRepo.findById(memberNo).orElseThrow();
		
		member.changePassword(passEncoder.encode(newPass));
		memberRepo.save(member);
	}

	//현재 비밀번호 일치 검사
	@Override
	public boolean checkpass(long memberNo, String pass) {
		return passEncoder.matches(pass, memberRepo.findById(memberNo).orElseThrow().getPassword());
	}

}

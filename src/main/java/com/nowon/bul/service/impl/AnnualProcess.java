package com.nowon.bul.service.impl;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.domain.dto.annual.AnnualApproveCode;
import com.nowon.bul.domain.dto.annual.AnnualCancelDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.mybatis.mapper.AnnualApprovalMapper;
import com.nowon.bul.mybatis.mapper.AnnualMapper;
import com.nowon.bul.service.AnnualService;
import com.nowon.bul.service.ApprovalService;
import com.nowon.bul.service.MemberService;
import com.nowon.bul.utils.AuthenUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnnualProcess implements AnnualService {
	
	private final AnnualMapper mapper;
	private final ApprovalService approvalService;
	private final MemberService memberService;
	private final AnnualApprovalMapper annualApprovalMapper;

	@Override
	@Transactional
	public void save(AnnualSaveDTO dto, Authentication auth) {
		dto.setLine(annualApprovalMapper.findAll());
		dto.setApprove(AnnualApproveCode.PROGRESS.getApproveCode());
		dto.setMemberNo(AuthenUtils.extractMemberNo(auth));
		
		mapper.annuSave(dto); // 휴가신청이력에 추가
		RequestApproval(dto, auth.getName()); // 결재요청
	}

	//결재서비스로 요청하는 메서드
	private void RequestApproval(AnnualSaveDTO dto, String name) {
		
		ApprovalDTO requestDTO =new ApprovalDTO();
		//사원번호 + 이름 + 휴가유형 + 휴가일수
		requestDTO.setTitle(dto.getMemberNo()+name+dto.getType()+dto.daysDifference()+"일");
		requestDTO.setContent(dto.getContent());
		// 휴가 결재라인 입력
		requestDTO.setLine(dto.getLine());
		approvalService.saveApproval(requestDTO, memberService.getFindById(dto.getMemberNo()));
	}

	@Override
	public void list(Authentication auth, Model model) {
		long memberNo = AuthenUtils.extractMemberNo(auth);
		//휴가신청현황
		model.addAttribute("liveList",mapper.findByLiveMemberNo(memberNo));
		//휴가이력
		model.addAttribute("list",mapper.findByMemberNo(memberNo));
	}

	@Override
	public void cancel(Authentication auth, long annualNo) {
		mapper.cancelByNo(AnnualCancelDTO.builder()
				.memberNo(AuthenUtils.extractMemberNo(auth)) // AuthenUtils를 이용하여 Authentication 객체에서 memberNo 추출
				.annualNo(annualNo)
				.approveCode(AnnualApproveCode.CANCEL.getApproveCode())//enum에서 휴가신청취소코드 출력 
				.build());
	}

}

package com.nowon.bul.service.impl;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.domain.dto.annual.AnnualApproveCode;
import com.nowon.bul.domain.dto.annual.AnnualCancelDTO;
import com.nowon.bul.mybatis.mapper.AnnualMapper;
import com.nowon.bul.service.AnnualService;
import com.nowon.bul.utils.AuthenUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnnualProcess implements AnnualService {
	
	private final AnnualMapper mapper;

	@Override
	public void save(AnnualSaveDTO dto, Authentication auth) {
		dto.setHead("병욱");
		dto.setMemberNo(AuthenUtils.extractMemberNo(auth));
		mapper.annuSave(dto); // 휴가이력에 추가
	}

	@Override
	public void list(Authentication auth, Model model) {
		model.addAttribute("list",mapper.findByMemberNo(AuthenUtils.extractMemberNo(auth)));
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

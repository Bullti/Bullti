package com.nowon.bul.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.mybatis.mapper.AnnualMapper;
import com.nowon.bul.service.AnnualService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnnualProcess implements AnnualService {
	
	private final AnnualMapper mapper;

	@Override
	public void save(AnnualSaveDTO dto, Authentication auth) {
		dto.setHead("병욱");
		dto.setMemberNo(1L);
		mapper.annuSave(dto);
	}
}

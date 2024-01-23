package com.nowon.bul.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.domain.dto.annual.AnnualListDTO;
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
		dto.setMemberNo(1L);
		mapper.annuSave(dto);
	}

	@Override
	public void list(Authentication auth, Model model) {
		
		model.addAttribute("list",mapper.findByMemberNo(AuthenUtils.nameToLong(auth)));
	}
}

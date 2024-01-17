package com.nowon.bul.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.mybatis.mapper.AttendanceMapper;
import com.nowon.bul.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceProcess implements AttendanceService {
	
	private final AttendanceMapper mapper;

	@Override
	public void workIn(Authentication auth) {
		
		mapper.workIn(Long.valueOf(auth.getName()));
	}

	@Override
	public void find(Authentication auth, Model model) {
		model.addAttribute("atteList", mapper.find(5959L));
		
	}
}

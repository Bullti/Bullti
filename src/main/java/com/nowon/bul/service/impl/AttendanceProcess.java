package com.nowon.bul.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.attendance.AttendanceCheckDTO;
import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.mybatis.mapper.AttendanceMapper;
import com.nowon.bul.service.AttendanceService;
import com.nowon.bul.utils.AuthenUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceProcess implements AttendanceService {
	
	private final AttendanceMapper mapper;

	@Override
	public void workIn(Authentication auth) {
		mapper.workIn(AttendanceCheckDTO.builder()
				.memberNo(AuthenUtils.extractMemberNo(auth))
				.regiType(1)
				.build());
	}

	@Override
	public void find(Authentication auth, Model model) {
		model.addAttribute("atteList", mapper.find(AuthenUtils.extractMemberNo(auth)));
		
	}
}

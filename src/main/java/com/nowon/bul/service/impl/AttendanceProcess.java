package com.nowon.bul.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.attendance.AttendanceCheckDTO;
import com.nowon.bul.domain.dto.attendance.AttendanceDTO;
import com.nowon.bul.mybatis.mapper.AttendanceMapper;
import com.nowon.bul.service.AttendanceService;
import com.nowon.bul.utils.AuthenUtils;
import com.nowon.bul.utils.PageData;

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
	public void find(int page, Authentication auth, Model model) {
	    int limit=15; // 한페이지 표현할 rows수
	    int offset=(page-1)*limit; // 건너뛰는 개수 1page={offset=0}
	    
		model.addAttribute( "pu", PageData.create(page, limit, 
				mapper.countAllById(AuthenUtils.extractMemberNo(auth)),5) );
		model.addAttribute("atteList", mapper.find(AuthenUtils.extractMemberNo(auth),limit, offset));
	}

	@Override
	public String workingStatus(Authentication auth) {
		String result = "";
		//가장 최근 출근기록을 불러옵니다
		AttendanceDTO Resultdto = mapper.findStatus(AuthenUtils.extractMemberNo(auth));
		if(Resultdto.getLeaveWorkTime()==null) {
			result = Resultdto.getGoWorkTime().toString();
		} else {
			result = "";
		};
		return result;
	}
}

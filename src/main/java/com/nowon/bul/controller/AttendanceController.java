package com.nowon.bul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.service.AttendanceService;
import com.nowon.bul.service.impl.AttendanceProcess;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping ("/atte")
public class AttendanceController {
	
	private final AttendanceService attendanceService;
	
	//출근
	@PostMapping
	@ResponseBody
	public void checkInAtWork(Authentication auth) {
		attendanceService.checkIn(auth);
	}
}

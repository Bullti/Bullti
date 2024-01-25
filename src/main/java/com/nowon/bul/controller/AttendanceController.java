package com.nowon.bul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.service.AttendanceService;
import com.nowon.bul.service.impl.AttendanceProcess;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping ("/emp/atte")
public class AttendanceController {
	
	private final AttendanceService attendanceService;
	
	//출근
	@PostMapping
	public String checkInAtWork(Authentication auth) {
		attendanceService.workIn(auth);
		return "redirect:/emp/atte";
	}
	
	@GetMapping
	public String attendanceList(Authentication auth, Model model) {
		attendanceService.find(auth, model);
		return "views/emp/atten/list";
	}
}

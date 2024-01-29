package com.nowon.bul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.service.AttendanceService;

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
	public String attendanceList(@RequestParam(name = "page",defaultValue = "1") int page
			,Authentication auth, Model model) {
		attendanceService.find(page, auth, model);
		return "views/emp/atten/list";
	}
	
	//출근시간 반환
	@ResponseBody
	@GetMapping("/status")
	public String attendanceList(Authentication auth) {
		return attendanceService.workingStatus(auth);
	}
}

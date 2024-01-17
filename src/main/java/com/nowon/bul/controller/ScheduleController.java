package com.nowon.bul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp/schedule")
public class ScheduleController {

	@GetMapping
	public String attendanceList(Authentication auth, Model model) {
		return "views/emp/atten/list";
	}
}

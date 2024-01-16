package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApprovalController {

	@GetMapping("/approval")
	public String approvalPage() {
		return "/views/approval";
	}
	
}

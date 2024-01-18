package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/approval")
@Controller
public class ApprovalController {

	@GetMapping("")
	public String approvalModal() {
		return "/views/approval/home";
	}
	
	@GetMapping("/write")
	public String writePage() {
		
		return "/views/approval/write";
	}
	
	//임시저장함
	@GetMapping("/temp/list")
	public String tempLlist() {
		
		return "/views/approval/tempList";
	}
}

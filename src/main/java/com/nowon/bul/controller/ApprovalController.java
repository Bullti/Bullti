package com.nowon.bul.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowon.bul.department.DeService;
import com.nowon.bul.service.ApprovalService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/approval")
@Controller
public class ApprovalController {

	
	private final ApprovalService approvalService;
	
	private final DeService deService;
	
	@GetMapping("")
	public String approvalModal() {
		return "/views/approval/home";
	}
	
	@GetMapping("/write")
	public String writePage(Model model) {
		//List<Map<String, Object>> deptList = deService 
		
		
		return "/views/approval/write";
	}
	
	//임시저장함
	@GetMapping("/temp/list")
	public String tempLlist() {
		
		return "/views/approval/tempList";
	}
}

package com.nowon.bul.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowon.bul.department.DeListDTO;
import com.nowon.bul.department.DeService;

import com.nowon.bul.service.ApprovalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/approval")
@Controller
public class ApprovalController {

	private final DeService deptService;
	
	private final ApprovalService approvalService;
	
	private final DeService deService;
	
	@GetMapping("")
	public String approvalModal() {
		return "/views/approval/home";
	}
	
	@GetMapping("/write")
	public String writePage(Model model) {
		List<DeListDTO> deptList = deptService.getList();
		
		model.addAttribute("deptList", deptList);
		
		return "/views/approval/modal";
	}
	
	//임시저장함
	@GetMapping("/temp/list")
	public String tempLlist() {
		
		return "/views/approval/tempList";
	}
}

package com.nowon.bul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.department.DeController;
import com.nowon.bul.department.DeService;
import com.nowon.bul.domain.dto.MemberDTO;
import com.nowon.bul.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	@Autowired
	DeService deService;
	
	private final MemberService memberSerivce; 
	private final DeController deController;
	
	@GetMapping("/temp")
	public String tempPage() {
		return "/department/temp";
	}
	
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception,
			Model model) {
		//System.out.println(error);
		//System.out.println(exception);
		//model.addAttribute("error", error);
		//model.addAttribute("exception", exception);
		return "/views/login";
	}
	
	@GetMapping("/members")
	public String joinPage() {
		return "/views/members/signup";
	}
	
	@GetMapping("/members/list")
	public String listPage(Model model) {
		
		 List<String> departmentNames = deService.getDepartmentNames(); // 상위부서 목록을 가져옴
	     model.addAttribute("names", departmentNames); // 뷰로 전달
		
		return "/views/members/list";
	}
	
	@PostMapping("/members")
	public String join(MemberDTO dto) {
		memberSerivce.save(dto);
		return "redirect:/members";
	}
	
}

package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nowon.bul.domain.dto.MemberDTO;
import com.nowon.bul.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final MemberService memberSerivce; 
	
	@GetMapping("/temp")
	public String tempPage() {
		return "/department/temp";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "/views/login";
	}
	
	@GetMapping("/members")
	public String joinPage() {
		return "/views/signup";
	}
	
	@PostMapping("/members")
	public String join(MemberDTO dto) {
		memberSerivce.save(dto);
		return "redirect:/members";
	}
	
}

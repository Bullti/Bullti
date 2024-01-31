package com.nowon.bul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.service.AnnualService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/emp/annu")
public class AnnualController {
	
	private final AnnualService servie;


	@GetMapping
	public String annualPage() {
		return "views/emp/annual/annual";
	}
	@PostMapping
	public String annualsave(AnnualSaveDTO dto,Authentication auth) {
		servie.save(dto, auth);
		return "views/emp/annual/annual";
	}
	
	@GetMapping("/list")
	public String annualList(Authentication auth,Model model) {
		servie.list(auth,model);
		return "views/emp/annual/list";
	}
	
	@DeleteMapping
	public String annualList(Authentication auth,@RequestParam("annualNo") long annualNo) {
		System.out.println("델리트매핑테스트"+annualNo);
		servie.cancel(auth, annualNo);
		return "redirect:/emp/annu";
	}
}

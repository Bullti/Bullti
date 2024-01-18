package com.nowon.bul.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.service.AnnualService;
import com.nowon.bul.service.impl.AnnualProcess;

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
}

package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/temp")
	public String tempPage() {
		return "/department/temp";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "/views/login";
	}
	
}

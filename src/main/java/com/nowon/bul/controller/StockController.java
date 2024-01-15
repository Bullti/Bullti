package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {
	
	@GetMapping("/members/dashboard")
	public String stockdashboard() {
		return "stock/dashboard";
	}
	
	@GetMapping("/members/notice")
	public String notice() {
		return "views/notice";
	}
	
}

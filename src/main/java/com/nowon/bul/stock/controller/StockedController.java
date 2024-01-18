package com.nowon.bul.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockedController {
	
	@GetMapping("/members/stocked")
	public String stocked() {
		
		return "stock/stocked";
		
	}
	
}

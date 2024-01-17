package com.nowon.bul.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {
	
	@GetMapping("/members/stock")
	public String stock() {
		return "stock/stock";
	}
	
}

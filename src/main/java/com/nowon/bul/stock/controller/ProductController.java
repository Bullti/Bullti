package com.nowon.bul.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	@GetMapping("/members/product")
	public String product() {
		
		return "stock/product";
	}
	

}

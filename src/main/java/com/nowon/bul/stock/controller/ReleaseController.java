package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nowon.bul.stock.service.ProductService;

@Controller
public class ReleaseController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("members/release")
	public String release(Model model) {
		
		model.addAttribute("products",productService.getAllProducts());
		
		return "stock/release";
		
	}
	
	@GetMapping("members/release-post")
	public String release_post() {
		
		return "stock/release-post";
	}
	
	@GetMapping("members/release-complete")
	public String release_complete() {
		return "stock/release-complete";
	}
	
}

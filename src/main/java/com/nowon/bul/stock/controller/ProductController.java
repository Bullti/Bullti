package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nowon.bul.stock.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/members/product")
	public String product() {
		
		return "stock/product";
	}
	
	
	

}

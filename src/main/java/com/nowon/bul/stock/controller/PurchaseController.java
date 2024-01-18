package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nowon.bul.stock.service.ProductService;

@Controller
public class PurchaseController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/members/purchase")
	public String purchase(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "stock/purchase";
	}
	
	@GetMapping("/members/purchase-post")
	public String purchase_post() {
		return "stock/purchase-post";
	}
	
}

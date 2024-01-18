package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.stock.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/members/product")
    public String product(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "stock/product"; // productPage는 Thymeleaf 또는 JSP 페이지의 이름입니다.
    }
	
	@GetMapping("/members/product-post")
	public String product_post() {
		
		return "stock/product-post";
	}
	

}

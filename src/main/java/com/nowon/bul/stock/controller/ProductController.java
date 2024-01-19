package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.stock.dto.ProductDTO;
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
	public String product_post(Model model) {
		 ProductDTO productDTO = ProductDTO.builder().build();
		    model.addAttribute("productDTO", productDTO);
	    return "stock/product-post";
	}

	
	@PostMapping("/members/product-registration")
	public String productRegister(@ModelAttribute("productDTO") ProductDTO productDTO, BindingResult bindingResult) {
			productService.registerProduct(productDTO);
		return "redirect:/members/product";
	}
	
	

}

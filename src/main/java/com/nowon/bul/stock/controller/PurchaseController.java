package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.stock.dto.PurchaseDTO;
import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.entity.PurchaseEntity;
import com.nowon.bul.stock.repository.PurchaseRepository;
import com.nowon.bul.stock.service.ProductService;
import com.nowon.bul.stock.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	
	@GetMapping("/members/purchase")
	public String purchase(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "stock/purchase";
	}
	
	
	
	@GetMapping("/members/purchase-post")
	public String purchase_post(Model model) {
	 
	   
	    model.addAttribute("purchases", purchaseService.getAllPurchases());
	    return "stock/purchase-post";
	}
	
	@ResponseBody
	@PostMapping("/members/purchase-post")
	public String purchase_post(@RequestBody PurchaseDTO dto) {
	    System.out.println(">>>>" + dto.getProductName());
	    System.out.println(">>>>" + dto.getEa());

	    // 상품 조회
	    ProductEntity product = productService.getProductByName(dto.getProductName());

	    // PurchaseEntity 생성
	    PurchaseEntity purchaseEntity = PurchaseEntity.builder()
	            .product(product)
	            .ea(dto.getEa())
	            .build();

	    // 생성된 PurchaseEntity를 DB에 저장
	    purchaseRepository.save(purchaseEntity);

	    return "등록성공";
	}
	
	@GetMapping("/members/purchase-complete")
	public String purchase_complete() {
		
		return "stock/purchase-complete";
	}

	
	// 상품 삭제
	@PostMapping("/members/purchase-post/{purchaseNum}")
	public String deletePurchase(@PathVariable("purchaseNum") int purchaseNum) {
	    purchaseService.deletePurchase(purchaseNum);
	    return "redirect:/members/purchase-post";
	}


	
	
}
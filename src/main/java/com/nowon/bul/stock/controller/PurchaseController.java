package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nowon.bul.stock.dto.PurchaseDTO;
import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.entity.PurchaseEntity;
import com.nowon.bul.stock.repository.PurchaseRepository;
import com.nowon.bul.stock.service.ProductService;
import com.nowon.bul.stock.service.PurchaseService;

@Controller
@RequestMapping("/members")  // 클래스 레벨에서 공통 경로 설정
public class PurchaseController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private PurchaseService purchaseService;
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    
    @GetMapping("/purchase")
    public String purchase(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "stock/purchase";
    }
    
    @GetMapping("/purchase-post")
    public String purchase_post(Model model) {
        model.addAttribute("purchases", purchaseService.getAllPurchases());
        return "stock/purchase-post";
    }
    
    @ResponseBody
    @PostMapping("/purchase-post")
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
    
        return "등록 성공";
    }
    
    @GetMapping("/purchase-complete")
    public String purchase_complete() {
        return "stock/purchase-complete";
    }

    // 상품 삭제
    @DeleteMapping("/purchase-post/{purchaseNum}")
    public String deletePurchase(@PathVariable("purchaseNum") int purchaseNum) {
        purchaseService.deletebyid(purchaseNum);
        return "redirect:/members/purchase-post";  // 삭제 후 목록으로 리다이렉션
    }
}

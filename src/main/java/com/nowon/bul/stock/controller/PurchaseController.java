package com.nowon.bul.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nowon.bul.domain.entity.fran.FranEntity;
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
    
        purchaseService.purchaseSave(dto);
    
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
    
    @DeleteMapping("/purchase-post/all")
    public String deleteAllPurchases() {
        purchaseService.deleteAllPurchases();
        return "redirect:/members/purchase-post";  // 삭제 후 리다이렉트할 페이지
    }
    
    /*
    @PostMapping("/purchase-post")
    public String submitPurchase(PurchaseDTO purchaseDTO, Authentication auth,Model model) {
        // 현재 인증된 사용자의 정보를 가져옵니다.
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        // 발주 정보를 'purchase-complete' 테이블에 저장합니다.
        purchaseService.savePurchaseComplete(purchaseDTO, username,model);
        model.addAttribute("purCom",purchaseService.getAllPurchaseComplements());

        // 'purchase' 테이블의 모든 데이터를 삭제합니다.
        purchaseService.deleteAllPurchases();

        // '발주 신청 현황' 페이지로 리다이렉트합니다.
        return "redirect:/members/purchase-complete";
    }

    */
    
}

package com.nowon.bul.stock.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.stock.dto.PurchaseDTO;

public interface PurchaseService {
	
	
	List<PurchaseDTO> getAllPurchases();
	
	void purchaseSave(PurchaseDTO dto);

	void deletePurchase(@PathVariable("purchaseNum") int purchaseNum);

}

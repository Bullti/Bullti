package com.nowon.bul.stock.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.nowon.bul.stock.dto.PurchaseCompleteDTO;
import com.nowon.bul.stock.dto.PurchaseDTO;

public interface PurchaseService {
	
	
	List<PurchaseDTO> getAllPurchases();
	
	void purchaseSave(PurchaseDTO dto);

	void deletebyid(int purchaseNum);

	void deleteAllPurchases();
	 
	void savePurchaseComplete(PurchaseDTO purchaseDTO, String username,Model model);

	List<PurchaseCompleteDTO> getAllPurchaseComplements();

	void movePurchase(Authentication auth,Model model);

}

package com.nowon.bul.stock.service;

import java.util.List;

import com.nowon.bul.stock.dto.PurchaseDTO;

public interface PurchaseService {
	
	/*
	List<PurchaseDTO> getAllPurchases();
	*/
	
	
	void purchaseSave(PurchaseDTO dto);

}

package com.nowon.bul.stock.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nowon.bul.stock.dto.PurchaseDTO;
import com.nowon.bul.stock.entity.PurchaseEntity;
import com.nowon.bul.stock.repository.ProductRepository;
import com.nowon.bul.stock.repository.PurchaseRepository;
import com.nowon.bul.stock.service.PurchaseService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{

	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;
	
	
	@Override
	public void purchaseSave(PurchaseDTO dto) {
		
		//PurchaseEntity 생성
		PurchaseEntity purchaseEntity = PurchaseEntity.builder()
				.product(dto.getProduct())
				.ea(dto.getEa())
				.build();
		
		//생성된 PurchaseEntity를 DB에 저장
		purchaseRepository.save(purchaseEntity);
		
		
	}

	/*
	@Override
	public List<PurchaseDTO> getAllPurchases() {
		
		return purchaseRepository.findAll().stream()
				.map(PurchaseEntity::toPurchaseDTO)
				.collect(Collectors.toList())
				;
	}
	*/
	
	
	
}

package com.nowon.bul.stock.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.entity.FranEntity;
import com.nowon.bul.domain.entity.FranEntityRepository;
import com.nowon.bul.stock.dto.PurchaseDTO;
import com.nowon.bul.stock.entity.ProductEntity;
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
	
	@Autowired
    private final FranEntityRepository franEntityRepository; 
	
	
	public void purchaseSave(PurchaseDTO dto) {

		//PurchaseEntity 생성
		PurchaseEntity purchaseEntity = PurchaseEntity.builder()

		.ea(dto.getEa())
		.build();

		//생성된 PurchaseEntity를 DB에 저장
		purchaseRepository.save(purchaseEntity);

		}

	
	@Override
	public List<PurchaseDTO> getAllPurchases() {
		
		return purchaseRepository.findAll().stream()
				.map(PurchaseEntity::toPurchaseDTO)
				.collect(Collectors.toList())
				;
	}
	
	
	
	
}

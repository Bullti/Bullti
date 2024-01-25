package com.nowon.bul.stock.dto;

import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.entity.PurchaseEntity;
import com.nowon.bul.stock.entity.PurchaseEntity.PurchaseEntityBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PurchaseDTO {
	
	private int purchaseNum;
	
	private ProductEntity product;
	private int ea;
	
	
	public PurchaseEntity toEntity() {
		
		return PurchaseEntity.builder()
				.product(product)
				.ea(ea)
				.build();
	
		
	}
	
	
	
	

}

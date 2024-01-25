package com.nowon.bul.stock.dto;

import java.time.LocalDateTime;

import com.nowon.bul.stock.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
public class ProductDTO {
	
	private int productNum;
	
	private String productName;
	
	private int productPrice;
	
	private int productWeight;
	
	private String authorName;
		
	private LocalDateTime productDate;
	
	private String productGroup;
	
	private int productSupply;
	
	private boolean deleteProdYn;
	

	public ProductEntity toEntity() {
		
		return ProductEntity.builder()
				.productName(productName)
				.productGroup(productGroup)
				.productWeight(productWeight)
				.productSupply(productSupply)
				.productPrice(productPrice)
				.build();
	}
	
	
	
}

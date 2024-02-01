package com.nowon.bul.stock.dto;

import java.time.LocalDateTime;

import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.entity.PurchaseEntity;
import com.nowon.bul.stock.entity.PurchaseEntity.PurchaseEntityBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
	
	private String productName;
	private int purchaseNum;
	private String franchiseName;//가맹점이름
	private int totalPrice;
	private LocalDateTime purchaseDate;
	private int ea;
	private int productPrice;
	


}

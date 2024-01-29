package com.nowon.bul.stock.dto;

import java.time.LocalDateTime;

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
	
	private int purchaseNum;
	private String productName;
	private String name;
	private int totalPrice;
	private LocalDateTime purchaseDate;
	private int ea;
	private int productPrice;
	


}
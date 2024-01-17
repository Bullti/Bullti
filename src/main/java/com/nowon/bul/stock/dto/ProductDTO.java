package com.nowon.bul.stock.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
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
	
}

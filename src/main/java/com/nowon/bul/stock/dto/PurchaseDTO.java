package com.nowon.bul.stock.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	
	private String name;
	private String productName;
	private int purchaseNum;
	private int totalPrice;
	private int totalWeight;
	private LocalDateTime purchaseDate;
	private int ea;
	private int productPrice;
	
	
	public String getFormattedTotalPrice() {
	    NumberFormat formatter = NumberFormat.getInstance();
	    return formatter.format(totalPrice);
	}

	
	public String getFormattedTotalWeight() {
	    if (totalWeight >= 1000) {
	        DecimalFormat df = new DecimalFormat("#.#");
	        return df.format(totalWeight / 1000.0) + "kg";
	    } else {
	        return totalWeight + "g";
	    }
	}
	
	public String getFormattedSupply() {
	    return ea + "개";
	}


}

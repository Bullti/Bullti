package com.nowon.bul.stock.dto;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import com.nowon.bul.stock.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	
	public String getFormattedWeight() {
        if (productWeight >= 1000) {
            DecimalFormat df = new DecimalFormat("#.#");
            return df.format(productWeight / 1000.0) + "kg";
        } else {
            return productWeight + "g";
        }
    }


    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("#,###");
        return "￦" + df.format(productPrice);
    }
    
    
    public String getFormattedSupply() {
        DecimalFormat df = new DecimalFormat("#,###");
        return "￦" + df.format(productSupply);
    }
	
	
	
}

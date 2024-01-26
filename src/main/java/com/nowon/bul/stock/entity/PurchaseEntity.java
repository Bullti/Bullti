package com.nowon.bul.stock.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nowon.bul.domain.entity.FranEntity;
import com.nowon.bul.stock.dto.PurchaseDTO;
import com.nowon.bul.stock.dto.PurchaseDTO.PurchaseDTOBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PurchaseEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseNum;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_num")
	private ProductEntity product;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "franchise_id")
	private FranEntity franchise;
	
	
	@Column(nullable = false)
	private int ea;
	
	@Column(nullable = false)
	private int totalPrice;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null", nullable = false)
	private LocalDateTime purchaseDate;
	
	
	
	public PurchaseDTO toPurchaseDTO(){
		
		 PurchaseDTOBuilder builder = PurchaseDTO.builder();

		    if (product != null) {
		        builder.productName(product.getProductName())
		               .ea(ea)
		               .totalPrice(this.calculateTotalPrice());
		    }

		    if (franchise != null) {
		        builder.franchiseName(franchise.getName());
		    }

		
		
		    return builder.purchaseDate(purchaseDate).build();
	}
		
	private int calculateTotalPrice() {
		return ea * product.getProductPrice();
	}
	

}

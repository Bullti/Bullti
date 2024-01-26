package com.nowon.bul.stock.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nowon.bul.domain.entity.FranEntity;
import com.nowon.bul.stock.dto.PurchaseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

	
	
	@ManyToOne
	@JoinColumn(name = "product_num")
	private ProductEntity product;
	
	
	@ManyToOne
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
		
		return PurchaseDTO.builder()
				.productName(product.getProductName())
				.franchiseName(franchise.getName())
				.ea(ea)
				.totalPrice(this.calculateTotalPrice())
				.purchaseDate(purchaseDate)
				.build();
	}
		
	private int calculateTotalPrice() {
		return ea * product.getProductPrice();
	}
	
	
	
	
	
	
}

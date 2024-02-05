package com.nowon.bul.stock.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nowon.bul.domain.entity.fran.FranEntity;
import com.nowon.bul.domain.entity.member.Member;
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
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_num")
	private ProductEntity product;
	
	
	
	
	@Column(nullable = false)
	private int ea;
	
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null", nullable = false)
	private LocalDateTime purchaseDate;
	
	
	public PurchaseDTO toPurchaseDTO(){
		return PurchaseDTO.builder()
				.productName(product.getProductName())
				.purchaseNum(purchaseNum)
				.totalWeight(calculateTotalWeight())
				.totalPrice(calculateTotalPrice())
				.ea(ea)
				.purchaseDate(purchaseDate)
				.productPrice(product.getProductPrice())
				.build();		
	}
		
	public int calculateTotalPrice() {
		return ea * product.getProductPrice();
	}

	public int calculateTotalWeight() {
		return ea * product.getProductWeight();
	}
	
	
	

}

package com.nowon.bul.stock.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.stock.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class ProductEntity extends BaseEntity{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int productNum;
	 
	 @Column(nullable = false)
	 private String productName;
	 
	 @Column(nullable = false)
	 private int productPrice;
	 
	 @Column(nullable = false)
	 private int productWeight;
	 
	 //////////////////////////////////
	 //회원엔티티가  작성자 역할 들어가야함
	 @ManyToOne
	 @JoinColumn(name = "member_no")
	 private Member member;
	 
	 @CreationTimestamp
	 @Column(columnDefinition = "timestamp(6) null" ,nullable = false)
	 private LocalDateTime productDate;
	 
	  
	 @Column(nullable = false)
	 private String productGroup;
	 
	 @Column(nullable = false)
	 private int productSupply;

	 @Column
	 private boolean deleteProdYn;
	 
	 public ProductDTO toProductDTO() {
		 
		 return ProductDTO.builder()
				.productNum(productNum)
				.productName(productName)
				.productPrice(productPrice)
				.productWeight(productWeight)
				.authorName(member!=null?member.getName():"테스트")
				.productDate(productDate)
				.productGroup(productGroup)
				.productSupply(productSupply)
				.deleteProdYn(deleteProdYn)
				.build();
				
				 
		 
	 }
	 
}

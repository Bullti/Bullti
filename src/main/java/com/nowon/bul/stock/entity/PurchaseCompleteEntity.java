package com.nowon.bul.stock.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nowon.bul.stock.dto.PurchaseCompleteDTO;

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
import lombok.Setter;

@Entity
@Table(name = "purchase_complete")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseCompleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    
    @ManyToOne
    @JoinColumn(name = "purchase_num")
    private PurchaseEntity purchase;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp(6) null" ,nullable = false)
    private LocalDateTime purchaseCompleteDate;
    
    
    public PurchaseCompleteDTO toPurchaseCompleteDTO() {
        return PurchaseCompleteDTO.builder()
                .id(id)
                .username(username)
                .productName(purchase.getProduct().getProductName())
                .quantity(purchase.getEa())
                .totalPrice(purchase.calculateTotalPrice())
                .purchaseCompleteDate(purchaseCompleteDate)
                .build();
    }
    
    
    
}

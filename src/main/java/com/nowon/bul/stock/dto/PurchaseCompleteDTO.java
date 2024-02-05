package com.nowon.bul.stock.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PurchaseCompleteDTO {
    private Long id;
    private String username;
    private String productName;
    private int quantity;
    private int totalPrice;
    private LocalDateTime purchaseCompleteDate;
}
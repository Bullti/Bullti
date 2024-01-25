package com.nowon.bul.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.bul.stock.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer>{

}

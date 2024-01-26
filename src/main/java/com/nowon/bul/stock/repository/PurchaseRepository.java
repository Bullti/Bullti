package com.nowon.bul.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nowon.bul.stock.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer>{

	@Query("SELECT p FROM PurchaseEntity p JOIN FETCH p.product JOIN FETCH p.franchise")
    List<PurchaseEntity> getAllPurchases();
	
	
}

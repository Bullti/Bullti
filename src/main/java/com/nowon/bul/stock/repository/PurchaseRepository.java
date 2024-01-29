package com.nowon.bul.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nowon.bul.stock.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer>{

	@Query("SELECT p FROM PurchaseEntity p JOIN FETCH p.product")
	List<PurchaseEntity> getAllPurchases();

	// 삭제 작업을 위한 메서드 추가
    void deleteByPurchaseNum(int purchaseNum);
	
}

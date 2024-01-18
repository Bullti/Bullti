package com.nowon.bul.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nowon.bul.stock.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	//데이터베이스와 상호작용을 담당(데이터를 읽고 쓰는 역할) 
	
	/*
	 * ProductRepository는 Spring Data JPA에서 제공하는 JpaRepository를 확장한 인터페이스로, 
	 * 데이터베이스와의 기본적인 CRUD(Create, Read, Update, Delete) 작업을 지원합니다. 
	 * ProductService에서는 이 레포지토리를 사용하여 사용자 정보를 저장하고 조회하는 서비스를 구현하고 있습니다.
	 * 레포지토리는 데이터베이스와의 통합을 담당하며, 서비스나 컨트롤러에서는 비즈니스 로직을 수행하고 필요할 때 
	 * 레포지토리를 호출하여 데이터를 영속화하거나 조회합니다. 
	 * 이렇게 하면 데이터베이스와의 상호작용이 간결하게 처리됩니다.
	 */
	
	
}

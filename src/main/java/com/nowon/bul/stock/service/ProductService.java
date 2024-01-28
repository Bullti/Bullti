package com.nowon.bul.stock.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.nowon.bul.stock.dto.ProductDTO;
import com.nowon.bul.stock.entity.ProductEntity;

public interface ProductService {
	
	List<ProductDTO> getAllProducts();
	
	String registerProduct(ProductDTO productDTO);
	
	ProductEntity getProductByName(String productName);
	
}

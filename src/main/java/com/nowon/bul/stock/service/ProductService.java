package com.nowon.bul.stock.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.nowon.bul.stock.dto.ProductDTO;

public interface ProductService {
	
	List<ProductDTO> getAllProducts();
	
	String registerProduct(ProductDTO productDTO);
	
	
	
}

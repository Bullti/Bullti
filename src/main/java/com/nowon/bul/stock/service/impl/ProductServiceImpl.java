package com.nowon.bul.stock.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import com.nowon.bul.stock.dto.ProductDTO;
import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.repository.ProductRepository;
import com.nowon.bul.stock.service.ProductService;

import lombok.RequiredArgsConstructor;



@Service
public class ProductServiceImpl implements ProductService{
	
	
	 
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream()
    		 .map(ProductEntity::toProductDTO)
    		 .collect(Collectors.toList());
    }
	
	public String registerProduct(ProductDTO productDTO) {
		
		productRepository.save(productDTO.toEntity());
		
		return "redirect:/members/product";
		
	}
	 
	
	@Override
	public ProductEntity getProductByName(String productName) {
	    return productRepository.findByName(productName);
	}
}

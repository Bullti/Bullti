package com.nowon.bul.stock.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.nowon.bul.stock.dto.ProductDTO;
import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.repository.ProductRepository;
import com.nowon.bul.stock.service.ProductService;

import software.amazon.awssdk.services.s3.S3Client;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private S3Client client;

	@Autowired
	private ProductRepository productRepository;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.upload-path}")
	private String uploadPath;
	@Value("${cloud.aws.s3.upload-temp}")
	private String tempPath;
	 
	@Override
	public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream()
    		 .map(ProductEntity::toProductDTO)
    		 .collect(Collectors.toList());
    }
	 
	@Override
	public void registerProduct(ProductDTO productDTO) {
		 
		ProductEntity productEntity = productRepository.save(productDTO.toEntity());
		
		
		
	}
	 
	 
	 
	 
}

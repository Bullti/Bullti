package com.nowon.bul.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.nowon.bul.domain.entity.fran.FranEntityRepository;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.stock.dto.PurchaseCompleteDTO;
import com.nowon.bul.stock.dto.PurchaseDTO;
import com.nowon.bul.stock.entity.ProductEntity;
import com.nowon.bul.stock.entity.PurchaseCompleteEntity;
import com.nowon.bul.stock.entity.PurchaseEntity;
import com.nowon.bul.stock.repository.ProductRepository;
import com.nowon.bul.stock.repository.PurchaseCompleteRepository;
import com.nowon.bul.stock.repository.PurchaseRepository;
import com.nowon.bul.stock.service.ProductService;
import com.nowon.bul.stock.service.PurchaseService;
import com.nowon.bul.utils.AuthenUtils;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{

	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;
	private final ProductService productService;
    private final FranEntityRepository franEntityRepository; 
    private final MemberRepository memberRepository;
	private final PurchaseCompleteRepository purchaseCompleteRepository;
	
	public void purchaseSave(PurchaseDTO dto) {

	
        // 상품 조회
        ProductEntity product = productService.getProductByName(dto.getProductName());
    
        // PurchaseEntity 생성
        PurchaseEntity purchaseEntity = PurchaseEntity.builder()
        		
                .product(product)
                .ea(dto.getEa())
                .build();
    
        // 생성된 PurchaseEntity를 DB에 저장
        purchaseRepository.save(purchaseEntity);

		}

	
	@Override
	public List<PurchaseDTO> getAllPurchases() {
		
		return purchaseRepository.findAll().stream()
				.map(PurchaseEntity::toPurchaseDTO)
				.collect(Collectors.toList())
				;
	}

	@Transactional
	@Override
	public void deletebyid(int purchaseNum) {
	  
		// 먼저 PurchaseEntity를 불러옵니다.
	    purchaseRepository.findById(purchaseNum).ifPresentOrElse(e->{
	    	purchaseRepository.delete(e);
	    }, () -> {
	        throw new EntityNotFoundException("PurchaseEntity with id " + purchaseNum + " not found");
	    });


	}
	
	@Transactional
	public void deleteAllPurchases() {
	    purchaseRepository.deleteAll();
	}
	
	
	 @Override
	    public List<PurchaseCompleteDTO> getAllPurchaseComplements() {
	        List<PurchaseCompleteEntity> purchaseCompleteEntities = purchaseCompleteRepository.findAll();
	        List<PurchaseCompleteDTO> purchaseCompleteDTOs = new ArrayList<>();

	        for (PurchaseCompleteEntity entity : purchaseCompleteEntities) {
	            purchaseCompleteDTOs.add(entity.toPurchaseCompleteDTO());
	        }

	        return purchaseCompleteDTOs;
	    }
	
	 @Transactional
	    @Override
	    public void savePurchaseComplete(PurchaseDTO purchaseDTO, String username,Model model) {
	        PurchaseCompleteEntity purchaseCompleteEntity = new PurchaseCompleteEntity();
	        // PurchaseCompleteEntity 객체에 데이터를 채웁니다.

	        purchaseCompleteRepository.save(purchaseCompleteEntity);
	    }


	 @Transactional
	 @Override
	 public void movePurchase(Authentication auth,Model model) {
		 
		Long memberId = AuthenUtils.extractMemberNo(auth);
		Integer memberIdInt = memberId.intValue();
		List<PurchaseEntity> entities = purchaseRepository.findAll();

			

		        for(PurchaseEntity entity : entities) {
		            PurchaseCompleteEntity completeEntity = new PurchaseCompleteEntity();
		            
		            
		            purchaseCompleteRepository.save(completeEntity);
		            
		            purchaseRepository.delete(entity);
		        }

		       
		     
	 }


	  

	
}

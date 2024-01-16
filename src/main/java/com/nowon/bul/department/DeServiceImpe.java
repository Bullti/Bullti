package com.nowon.bul.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeServiceImpe implements DeService {
	
	@Autowired
	DeRepository deRepository;
	
	/* 부서 저장 */
	public void deSave(DeEntity deEntity) {
		deRepository.save(deEntity);
	}
	
	
	/* 부서 조회 */
	
	public String deList(DeEntity deEntity) {
		deRepository.findAll();
		return null;
	}
	
	/* 부서 수정 */
	
	/* 부서 삭제 */
	

}

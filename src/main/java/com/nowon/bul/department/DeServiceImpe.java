package com.nowon.bul.department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeServiceImpe implements DeService {
	
	@Autowired
	DeRepository deRepository;
	
	/* 부서 저장 */
	public void deSave(DeEntity deEntity) {
		deRepository.save(deEntity);
	}
	
	
	/* 부서 조회 */
	
		@Transactional
		@Override
	    public List<Map<String, String>> getOrgChartData() {
	        List<Map<String, String>> orgChartData = new ArrayList<>();
	        System.out.println("바보세요?");
	        // 데이터베이스에서 정보를 가져와서 orgChartData에 추가하는 로직
	        List<DeEntity> departments = deRepository.findAll();
	        for (DeEntity department : departments) {
	        	System.out.println(">>>>>>>>>>"+department.toString());
	        	System.out.println(">>>>>>>>>>"+department.toString());
	        	System.out.println(">>>>>>>>>>"+department.toString());
	            Map<String, String> node = new HashMap<>();
	            node.put("name", department.getDeptName());
	            if(department.getParent() != null) {
	            	node.put("manager", department.getParent().getDeptName()); // 여기서 적절한 매니저 정보를 가져오는 로직 추가
	            }else {
	            	node.put("manager", "");
	            }
	            orgChartData.add(node);
	            
	        }

	        return orgChartData;
	    }
	
	/*
	 * public String deList(DeEntity deEntity) { deRepository.findAll(); }
	 */
	
	/* 부서 수정 */
	
	/* 부서 삭제 */
	

}

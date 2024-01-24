package com.nowon.bul.department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowon.bul.domain.dto.ApprovalDeptList;
import com.nowon.bul.domain.dto.DeptListDTO;

@Service
public class DeServiceImpe implements DeService {
	
	@Autowired
	DeRepository deRepository;
	
		/* 부서 저장 */
		/*
		 * @Override public void save(DeSaveDTO dto) {
		 * deRepository.save(dto.toEntity()); }
		 */

	
	/* 부서 조회 */
	
		@Transactional
		@Override
	    public List<Map<String, String>> getOrgChartData() {
	        List<Map<String, String>> orgChartData = new ArrayList<>();
	        // 데이터베이스에서 정보를 가져와서 orgChartData에 추가하는 로직
	        List<DeEntity> departments = deRepository.findAll();
	        for (DeEntity department : departments) {
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
		
		/* 부서 삭제 */
		
		@Transactional
		@Override
		public void delete(String name) {
			deRepository.deleteByDeptName(name);
		}

		@Transactional
		@Override
		public void save(DeSaveDTO dto) {
			DeEntity parent = deRepository.findByDeptName(dto.getParentName()).orElseThrow(); 
			System.out.println(parent.toString());
			
			
			deRepository.save(dto.toEntity(parent));
		}
		
	
		@Override
		    public List<String> getDepartmentNames() {
		        // 상위부서 목록을 가져오는 로직을 구현해야 합니다.
		        // 예시로 DeRepository에서 가져오는 코드를 넣어보겠습니다.
		        List<DeEntity> departments = deRepository.findAll();
		        List<String> departmentNames = new ArrayList<>();
		        for (DeEntity department : departments) {
		            departmentNames.add(department.getDeptName());
		        }
		        return departmentNames;
		    }

		//결재선 지정 부서 목록
		@Transactional
		@Override
		public List<ApprovalDeptList> getApprovalList() {
			return deRepository.findAll().stream()
					.map(DeEntity::toApprovalList)
					.collect(Collectors.toList());
		}
		
		//사원등록페이지 부서 리스트
		@Override
		public List<DeptListDTO> getDeptList() {
			return deRepository.findAll().stream().map(i -> 
			DeptListDTO.builder().deptName(i.getDeptName()).deptId(i.getDeptId()).build())
					.collect(Collectors.toList());
		}

 }
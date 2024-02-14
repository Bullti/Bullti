package com.nowon.bul.service;

import java.util.List;
import java.util.Map;

import com.nowon.bul.department.DeSaveDTO;
import com.nowon.bul.domain.dto.DeptListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDeptList;
import com.nowon.bul.domain.dto.dept.DeptSaveDTO;




public interface DeptService {

	List<Map<String, String>> getOrgChartData();
	
	List<String> getDepartmentNames(); 

	void delete(String name);

	void save(DeptSaveDTO dto);

	List<ApprovalDeptList> getApprovalList();
	
	
	//사원등록시 부서리스트
	List<DeptListDTO> getDeptList();

	
	/***********아래 전부 네이버 API 메서드************************/
	int getDeptId(String deptName);

	

}

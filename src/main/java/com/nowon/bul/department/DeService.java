package com.nowon.bul.department;

import java.util.List;
import java.util.Map;

import com.nowon.bul.domain.dto.DeptListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDeptList;




public interface DeService {

	List<Map<String, String>> getOrgChartData();
	
	List<String> getDepartmentNames(); 

	void delete(String name);

	void save(DeSaveDTO dto);

	List<ApprovalDeptList> getApprovalList();
	
	
	//사원등록시 부서리스트
	List<DeptListDTO> getDeptList();


	

}

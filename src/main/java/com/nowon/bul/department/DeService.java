package com.nowon.bul.department;

import java.util.List;
import java.util.Map;

import com.nowon.bul.domain.dto.ApprovalDeptList;




public interface DeService {

	List<Map<String, String>> getOrgChartData();
	
	List<String> getDepartmentNames(); 

	void delete(String name);

	void save(DeSaveDTO dto);

	List<ApprovalDeptList> getApprovalList();


	

}

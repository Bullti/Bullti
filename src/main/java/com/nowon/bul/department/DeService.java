package com.nowon.bul.department;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;



public interface DeService {

	List<Map<String, String>> getOrgChartData();
	
	List<String> getDepartmentNames(); 

	void delete(String name);

	void save(DeSaveDTO dto);

	//사원등록페이지 사용
	void getList(Model model);

	

}

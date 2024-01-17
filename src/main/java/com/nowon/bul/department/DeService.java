package com.nowon.bul.department;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public interface DeService {

	List<Map<String, String>> getOrgChartData();
}

package com.nowon.bul.department;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeController {
	
	@Autowired
	DeService deService;
	
	@GetMapping("/depart")
	public String delist() {

		return "department/temp";
	 }
	
	 @GetMapping("/depart/list")
	    public String showOrgChart(Model model) {
		 	System.out.println("이건 맞아?");
	        List<Map<String, String>> orgChartData = deService.getOrgChartData();
	        model.addAttribute("orgChartData", orgChartData);
	        return "department/deList";

	 }
}	 

package com.nowon.bul.department;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DeController {
	
	@Autowired
	DeService deService;
	
	@PostMapping("/department/deList")
	public String delete(HttpServletRequest request) {
	    String name = request.getParameter("name");
	    deService.delete(name);
	    return "redirect:/depart/list";
	}
	
	
	@GetMapping("/depart")
	public String delist() {

		return "department/temp";
	 }
	
	@GetMapping("/depart/save")
    public String desave(Model model) {
        List<String> departmentNames = deService.getDepartmentNames(); // 상위부서 목록을 가져옴
        model.addAttribute("names", departmentNames); // 뷰로 전달
        return "department/save";
    }
	
	@PostMapping("/depart")
	public String desave(DeSaveDTO dto) {
		System.out.println(dto.toString());
		deService.save(dto);
	    return "redirect:/depart/save";
	}
	
	 @GetMapping("/depart/list")
	    public String showOrgChart(Model model) {
	        List<Map<String, String>> orgChartData = deService.getOrgChartData();
	        model.addAttribute("orgChartData", orgChartData);
	       
	        return "department/deList";

	 }

	 
}	 

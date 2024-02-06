package com.nowon.bul.department;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.naver.NaverApiService;
import com.nowon.bul.naver.dto.NaverDeptSaveDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DeController {

	private final DeService deptService;
	private final NaverApiService naverApiService;

	@Transactional
	@PostMapping("/department/deList")
	public String delete(HttpServletRequest request) {
		String name = request.getParameter("name");
		
		////////////////////네이버API 메서드/////////////////////////////
		System.out.println("name= "+name);
		int deptId = deptService.getDeptId(name);
		naverApiService.DeleteDept(deptId);
		/////////////////////여기까지 네이버API////////////////////////////
		
		deptService.delete(name);
		
		return "redirect:/depart/list";
	}

	@GetMapping("/depart")
	public String delist() {
		return "department/temp";
	}

	@GetMapping("/depart/save")
	public String desave(Model model) {

		List<String> departmentNames = deptService.getDepartmentNames(); // 상위부서 목록을 가져옴
		model.addAttribute("names", departmentNames); // 뷰로 전달

		return "department/save";
	}
	
	
	@Transactional
	@PostMapping("/depart")
	public String desave(DeSaveDTO dto) {
		deptService.save(dto);

		//////////////////// 네이버API 메서드//////////////////////////////
		int parentDeptId = deptService.getDeptId(dto.getParentName());

		NaverDeptSaveDTO naverDeptSaveDTO = NaverDeptSaveDTO.builder().deptExternalKey("" + dto.getDeptId())
				.name(dto.getDeptName()).dispOrd("1").parentDeptExternalKey("" + parentDeptId).build();

		naverApiService.saveDept(naverDeptSaveDTO);
		///////////////////// 여기까지 네이버API////////////////////////////

		return "redirect:/depart/save";
	}

	@GetMapping("/depart/list")
	public String showOrgChart(Model model) {
		List<Map<String, String>> orgChartData = deptService.getOrgChartData();
		model.addAttribute("orgChartData", orgChartData);

		return "department/deList";

	}

}

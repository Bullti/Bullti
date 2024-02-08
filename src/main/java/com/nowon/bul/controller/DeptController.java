package com.nowon.bul.controller;

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

import com.nowon.bul.department.DeSaveDTO;
import com.nowon.bul.domain.dto.dept.DeptSaveDTO;
import com.nowon.bul.naver.NaverApiService;
import com.nowon.bul.naver.dto.NaverDeptSaveDTO;
import com.nowon.bul.service.DeptService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DeptController {

	private final DeptService deptService;
	private final NaverApiService naverApiService;

	/**************** 관리자 영역 ****************************/

	// 부서등록 페이지
	@GetMapping("/oms/depart")
	public String desave(Model model) {

		List<String> departmentNames = deptService.getDepartmentNames(); // 상위부서 목록을 가져옴
		model.addAttribute("names", departmentNames); // 뷰로 전달

		return "department/save";
	}

	// 부서리스트 페이지
	@GetMapping("/oms/depart/list")
	public String showOrgChart(Model model) {
		List<Map<String, String>> orgChartData = deptService.getOrgChartData();
		model.addAttribute("orgChartData", orgChartData);

		return "department/deList";

	}

	// 부서삭제
	@Transactional
	@PostMapping("/oms/department/deList")
	public String delete(HttpServletRequest request) {
		String name = request.getParameter("name");

		//////////////////// 네이버API 메서드/////////////////////////////
		System.out.println("name= " + name);
		int deptId = deptService.getDeptId(name);
		naverApiService.DeleteDept(deptId);
		///////////////////// 여기까지 네이버API////////////////////////////

		deptService.delete(name);

		return "redirect:/depart/list";
	}

	//부서등록
	@Transactional
	@PostMapping("/oms/depart")
	public String desave(DeptSaveDTO dto) {
		deptService.save(dto);

		//////////////////// 네이버API 메서드//////////////////////////////
		int parentDeptId = deptService.getDeptId(dto.getParentName());

		NaverDeptSaveDTO naverDeptSaveDTO = NaverDeptSaveDTO.builder().deptExternalKey("" + dto.getDeptId())
				.name(dto.getDeptName()).dispOrd("1").parentDeptExternalKey("" + parentDeptId).build();

		naverApiService.saveDept(naverDeptSaveDTO);
		///////////////////// 여기까지 네이버API////////////////////////////

		return "redirect:/depart/save";
	}

}

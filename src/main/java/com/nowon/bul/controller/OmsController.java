package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/oms")
@Controller
public class OmsController {

	@GetMapping("/emp")
	public String omsEmpPage() {
		return "/oms/emp";
	}
	
	@GetMapping("/dept")
	public String omsDeptPage() {
		return "/oms/dept";
	}
}

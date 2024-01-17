package com.nowon.bul.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeController {
	
	@Autowired
	DeService deService;
	
	@GetMapping("/depart")
	public String deSave() {
		
		
		
		return "department/save";
	}

}

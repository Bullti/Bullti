package com.nowon.bul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.service.FranListDTO;
import com.nowon.bul.service.FranProcess;
import com.nowon.bul.service.FranSaveDTO;
import com.nowon.bul.service.FranService;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FranController {
	
	@Autowired
	private FranService franService;
	
	@ResponseBody
	@GetMapping("/sl/fran")
    public List<FranListDTO> fr() {
        return franService.franList();
    }
	
	@GetMapping("/sl")
    public String storeLocation() {
        // 해당 경로에 대한 로직 처리
        return "/Franchise/sl";
    }
	
	@GetMapping("/fr")
	public String franchiseList(Model model) {
		franService.franchiseList(model);
		return "/Franchise/fr";
	}
	
	@GetMapping("/fradd")
	public String FranchiseAdd() {
		return "/Franchise/fradd";
	}
	
	@PostMapping("/fradd/register")
	public String registerFran(FranSaveDTO dto, Authentication auth) {
		//TODO: process POST request
		return franService.saveFran(dto, auth);
	}
	
	@PostMapping("/fr/register")
	public String franciseclose(Model model) {
		franService.franchiseclose(model);
		return "redirect:/fr";
	}
		
}

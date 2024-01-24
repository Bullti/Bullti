package com.nowon.bul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.domain.dto.FranListDTO;
import com.nowon.bul.domain.dto.FranSaveDTO;
import com.nowon.bul.service.FranProcess;
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
        return "/franchise/sl";
    }
	
	@GetMapping("/fr")
	public String franchiseList(Model model) {
		franService.franchiseList(model);
		return "/franchise/fr";
	}
	
	@GetMapping("/fradd")
	public String FranchiseAdd() {
		return "/franchise/fradd";
	}
	
	@PostMapping("/fradd/register")
	public String registerFran(FranSaveDTO dto, Authentication auth) {
		//TODO: process POST request
		return franService.saveFran(dto, auth);
	}
	
	@PostMapping("/fr/register")
	public String franciseclose(@RequestParam(name = "id") String id) {
	    System.out.println(id);
	    long parsedId = Long.parseLong(id);
	    franService.franchiseclose(parsedId);
	    return "redirect:/fr";
	}
	
	@GetMapping("/index2")
    public String index2() {
        // 해당 경로에 대한 로직 처리
        return "/views/index2";
    }
	
	@GetMapping("/fredit/{id}")
	public String franchiseedit(@PathVariable(name = "id") long id, Model model) {
	    franService.franchiseedit(id, model);
	    return "/franchise/fredit";
	}
}

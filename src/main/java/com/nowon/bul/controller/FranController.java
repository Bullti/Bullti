package com.nowon.bul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.bul.domain.dto.FranListDTO;
import com.nowon.bul.domain.dto.FranSaveDTO;
import com.nowon.bul.domain.dto.FranUpdateDTO;
import com.nowon.bul.service.impl.FranProcess;
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
		return "franchise/sl";
	}

	

	@GetMapping("/fradd")
	public String FranchiseAdd() {
		return "franchise/fradd";
	}

	@PostMapping("/fradd/register")
	public String registerFran(FranSaveDTO dto, Authentication auth) {
		// TODO: process POST request
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
		return "views/index2";
	}

	@GetMapping("/fredit/{id}")
	public String franchiseedit(@PathVariable(name = "id") long id, Model model) {
		franService.franchiseedit(id, model);
		return "franchise/fredit";
	}

	@PutMapping("/fredit/{id}")
	public String update(@PathVariable(name = "id") long id, FranUpdateDTO dto) {
		franService.updateProcess(id, dto);

		return "redirect:/fredit/" + id;
	}

	@GetMapping("/search")
    public String showSearchPage(Model model) {
		franService.ownerList(model);
		
        return "franchise/ownerlist";
    }
	
	@GetMapping("/fr")
	public String notice_post(
			@RequestParam(name="page",defaultValue = "1") int page,
			Model model) {
		
		franService.listProcess(page,model);
		
		return "franchise/fr";
	}
	
}

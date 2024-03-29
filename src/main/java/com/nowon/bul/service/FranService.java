package com.nowon.bul.service;

import java.util.List;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.FranListDTO;
import com.nowon.bul.domain.dto.FranSaveDTO;
import com.nowon.bul.domain.dto.FranUpdateDTO;

public interface FranService {

	String saveFran(FranSaveDTO dto, Authentication auth);

	List<FranListDTO> franList();

	void franchiseList(Model model);
	
	void franchiseedit(Long id, Model model);
	
	void franchiseclose(Long id);

	void updateProcess(Long id, FranUpdateDTO dto);

	void ownerList(Model model);

	void listProcess(int page, Model model);
	
}
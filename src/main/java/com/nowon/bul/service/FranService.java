package com.nowon.bul.service;

import java.util.List;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.ui.Model;

public interface FranService {

	String saveFran(FranSaveDTO dto, Authentication auth);

	List<FranListDTO> franList();

	void franchiseList(Model model);

	void franchiseclose(Long id);
}

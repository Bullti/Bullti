package com.nowon.bul.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.entity.FranEntity;
import com.nowon.bul.domain.entity.FranEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FranProcess implements FranService{

	private final FranEntityRepository franEntityRepository;

	public String saveFran(FranSaveDTO dto, Authentication auth) {
		
		franEntityRepository.save(dto.toEntity());
		
		return "redirect:/fr";
	}

	@Override
	public List<FranListDTO> franList() {
		// TODO Auto-generated method stub
		//List<FranEntity>
		//List<FranListDTO>
		/*
		List<FranEntity> result=franEntityRepository.findAll();
		List<FranListDTO> list=new ArrayList<>();
		for(FranEntity ent :result) {
			FranListDTO dto=ent.toFranListDTO();
			list.add(dto);
		}
		*/
		
		return franEntityRepository.findAll().stream()
				.map(FranEntity::toFranListDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void franchiseList(Model model) {
		model.addAttribute("list", franEntityRepository.findAll().stream()
				.map(FranEntity::toFranListDTO)
				.collect(Collectors.toList()));
	}

	@Override
    public void franchiseclose(Long id) {
        FranEntity entity = franEntityRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.closeFran();
            franEntityRepository.save(entity);
        }
    }
}

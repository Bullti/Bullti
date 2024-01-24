package com.nowon.bul.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.FranEditDTO;
import com.nowon.bul.domain.dto.FranListDTO;
import com.nowon.bul.domain.dto.FranSaveDTO;
import com.nowon.bul.domain.dto.FranUpdateDTO;
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

	@Override
	public void franchiseedit(Long id, Model model) {
	    // 특정 id에 해당하는 FranEntity 조회
	    FranEntity entity = franEntityRepository.findById(id).orElse(null);

	    if (entity != null) {
	        // FranEntity를 이용하여 필요한 데이터를 가져와서 Model에 추가
	        FranEditDTO dto = entity.toFranEditDTO(); // 예시: FranEntity에서 FranEditDTO로 변환하는 메소드 필요
	        model.addAttribute("franchiseEditDTO", dto);
	    }
	    // 원하는 뷰로 포워딩 또는 리다이렉트
	}

	@Override
    public void updateProcess(Long id, FranUpdateDTO dto) {
        FranEntity entity = franEntityRepository.findById(id).orElse(null);

        if (entity != null) {
            entity.updateFran(dto); // 예시: FranEntity에서 데이터 업데이트하는 메서드 필요
            franEntityRepository.save(entity);
        }
    }
}

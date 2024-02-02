package com.nowon.bul.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nowon.bul.domain.dto.FranEditDTO;
import com.nowon.bul.domain.dto.FranListDTO;
import com.nowon.bul.domain.dto.FranSaveDTO;
import com.nowon.bul.domain.dto.FranUpdateDTO;
import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.entity.fran.FranEntity;
import com.nowon.bul.domain.entity.fran.FranEntityRepository;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.domain.entity.member.Rank;
import com.nowon.bul.service.FranService;
import com.nowon.bul.utils.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FranProcess implements FranService {

	private final FranEntityRepository franEntityRepository;
	private final MemberRepository memberRepository;

	public String saveFran(FranSaveDTO dto, Authentication auth) {

		franEntityRepository.save(dto.toEntity(memberRepository));

		return "redirect:/fr";
	}

	@Override
	public List<FranListDTO> franList() {
		// TODO Auto-generated method stub
		// List<FranEntity>
		// List<FranListDTO>
		/*
		 * List<FranEntity> result=franEntityRepository.findAll(); List<FranListDTO>
		 * list=new ArrayList<>(); for(FranEntity ent :result) { FranListDTO
		 * dto=ent.toFranListDTO(); list.add(dto); }
		 */

		return franEntityRepository.findAll().stream().map(FranEntity::toFranListDTO).collect(Collectors.toList());
	}

	@Override
	public void franchiseList(Model model) {
		model.addAttribute("list",
				franEntityRepository.findAll().stream().map(FranEntity::toFranListDTO).collect(Collectors.toList()));
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

	@Override
	public void ownerList(Model model) {
		model.addAttribute("owner", memberRepository.findByRank(Rank.StoreManager).stream().map(Member::toFranOwnerDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public void listProcess(int page, Model model) {
		int limit = 10; //게시글 개수
	    //int offset = (page - 1) * limit; //건너뛰는 개수
	    
	    // PageRequest를 사용하여 페이징 정보를 생성
	    Pageable pageable = PageRequest.of(page - 1, limit);

	    // Spring Data JPA에서 페이징 처리를 위한 메서드를 사용
	    Page<FranEntity> franEntityPage = franEntityRepository.findAll(pageable);

	    List<FranListDTO> result = franEntityPage.getContent().stream()
	            .map(FranEntity::toFranListDTO)
	            .collect(Collectors.toList());
	    model.addAttribute("list", result);

	    int rowCount = (int) franEntityPage.getTotalElements();
	    model.addAttribute("pu", PageData.create(page, limit, rowCount, 5));
	}
	
	
}

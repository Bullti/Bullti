package com.nowon.bul.domain.dto;

import com.nowon.bul.domain.entity.FranEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class FranEditDTO {

	private Long id;
	
	private String name;
	private String address;
	private String address2;
	private String ph;

	public FranEntity toEntity() {
		return FranEntity.builder()
				.name(name).address(address).address2(address2).ph(ph)
				.build();
		
	}
}

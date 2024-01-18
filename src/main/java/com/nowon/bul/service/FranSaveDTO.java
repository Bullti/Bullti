package com.nowon.bul.service;

import com.nowon.bul.domain.entity.FranEntity;

import lombok.Setter;

@Setter
public class FranSaveDTO {

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

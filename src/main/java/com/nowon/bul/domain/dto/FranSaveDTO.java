package com.nowon.bul.domain.dto;

import com.nowon.bul.domain.entity.fran.FranEntity;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;

import lombok.Setter;

@Setter
public class FranSaveDTO {

	private String name;
	private String address;
	private String address2;
	private String ph;
	private Long MemberNo;

	public FranEntity toEntity(MemberRepository memberRepository) {
		return FranEntity.builder()
				.name(name).address(address).address2(address2).ph(ph).owner(memberRepository.findById(MemberNo).orElse(null))
				.build();
	}
}

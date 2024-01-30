package com.nowon.bul.domain.dto;

import java.time.LocalDate;

import com.nowon.bul.domain.entity.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FranOwnerDTO {
	
	private Long MemberNo;
	
	private String name;
	private LocalDate birthDate;

	public Member toEntity() {
		return Member.builder()
				.name(name)
				.birthDate(birthDate)
				.build();
	}
}

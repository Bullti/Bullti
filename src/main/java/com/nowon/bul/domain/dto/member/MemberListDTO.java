package com.nowon.bul.domain.dto.member;

import java.time.LocalDate;

import com.nowon.bul.domain.entity.member.Rank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberListDTO {
	
	private String id;
	private String name;
	private Rank rank;
	private String tel;
	private String deptName; 
	private LocalDate joinCompanyDate;
	private LocalDate resignationDate;
}

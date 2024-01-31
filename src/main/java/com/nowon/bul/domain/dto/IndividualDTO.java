package com.nowon.bul.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualDTO {

	private String profile;
	private String id;
	private String name;
	private String deptName;
	private String rank;
	private String tel;
	private String adress;
	private LocalDate birthDate;
	private LocalDate joinCompanyDate;
	
}

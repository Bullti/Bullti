package com.nowon.bul.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoticeSaveDTO {
	
	private String boardTitle;
	private String boardContent;
	private String name;
	private long deptId;
	private String deptName;
	private DeptListDTO dept; 
	
	
}

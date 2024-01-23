package com.nowon.bul.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class DeptListDTO {
	private int deptId;
	private String deptName;
}

package com.nowon.bul.domain.dto.dept;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class DeptListDTO {
	
	private int deptId;
	private String deptName;
	private int parentId;

}

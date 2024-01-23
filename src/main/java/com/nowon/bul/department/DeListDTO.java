package com.nowon.bul.department;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class DeListDTO {
	
	private int deptId;
	private String deptName;
	private int parentId;

}

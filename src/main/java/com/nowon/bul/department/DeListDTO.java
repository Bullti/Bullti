package com.nowon.bul.department;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeListDTO {
	
	private int deptId;
	private String deptName;

}

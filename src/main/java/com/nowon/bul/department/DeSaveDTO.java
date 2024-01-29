package com.nowon.bul.department;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeSaveDTO {
	
	private int deptId;
	private String deptName;
	private String parentName;
	
	
	
	public DeEntity toEntity(DeEntity parent) {
		return DeEntity.builder()
				.deptId(deptId)
				.deptName(deptName)
				.parent(parent)
				.build();
	}

}

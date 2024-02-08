package com.nowon.bul.domain.dto.dept;


import org.springframework.stereotype.Service;

import com.nowon.bul.domain.entity.dept.DeptEntity;
import com.nowon.bul.domain.entity.fran.FranEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeptSaveDTO {
	
	private int deptId;
	private String deptName;
	private String parentName;
	
	
	
	public DeptEntity toEntity(DeptEntity parent) {
		return DeptEntity.builder()
				.deptId(deptId)
				.deptName(deptName)
				.parent(parent)
				.build();
	}

}

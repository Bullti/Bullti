package com.nowon.bul.domain.dto.approval;

import java.util.List;

import com.nowon.bul.department.DeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class ApprovalDeptList {

	private int deptId;
	private String deptName;
	private int parentId;
	private List<ApprovalDeptList> child;

}

package com.nowon.bul.domain.dto.approval;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@ToString
@Getter
@Builder
public class ApprovalMemberDTO {
	private String name;
	private String deptName;
	private String rank;
}

package com.nowon.bul.domain.dto.approval;

import com.nowon.bul.domain.entity.member.Rank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApprovalMemberListDTO {
	private String name;
	private String rank;
	private String id;
}

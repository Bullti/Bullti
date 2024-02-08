package com.nowon.bul.domain.entity.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	
	USER("일반유저"),
	FR("프렌차이즈"),
	OMS("인사관리"),
	SMS("물류관리");
	
	private final String roleName;
}

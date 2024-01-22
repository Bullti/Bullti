package com.nowon.bul.domain.entity.member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Rank {
	
	Assistant("사원"),
	SeniorAssistant("대리"),
	Manager("과장"),
	SeniorManager("부장");
	
	private final String rankName;
}

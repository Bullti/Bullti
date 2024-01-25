package com.nowon.bul.domain.entity.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rank {
	
	Assistant("사원"),
	SeniorAssistant("대리"),
	Manager("과장"),
	SeniorManager("부장"),
	StoreManager("점장");
	
	private final String rankName;
	
}

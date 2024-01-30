package com.nowon.bul.domain.entity.approval;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {
	
	Temp("임시저장"),
	UnderReview("심사중"),
	Rejected("반려"),
	completed("결재완료");
	
	private final String sateName;
}

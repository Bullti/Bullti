package com.nowon.bul.domain.entity.approval;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Result {

	Wait("대기중"),
	UnderReview("심사중"),
	Reject("반려"),
	Accept("승인");
	
	private final String resultName;
}

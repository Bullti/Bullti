package com.nowon.bul.domain.dto.annual;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnnualApproveCode {

	ERROR(4, "오류"),
	PROGRESS(50, "진행중"),
    REJECT(90, "반려"),
    CANCEL(99, "취소"),
	APPROVE(100, "승인");

    private final int approveCode;
    private final String approveState;

    private static final Map<Integer, AnnualApproveCode> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(AnnualApproveCode::getApproveCode, Function.identity()));

    public static AnnualApproveCode valueOfNumber(int number) {
        return BY_NUMBER.getOrDefault(number, AnnualApproveCode.ERROR);
    }
}


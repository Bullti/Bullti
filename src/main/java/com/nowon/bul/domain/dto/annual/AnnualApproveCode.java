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

    APPROVE(50, "승인"),
    REJECT(90, "반려"),
    CANCEL(99, "취소");

    private final int approveCode;
    private final String approveState;

    private static final Map<Integer, AnnualApproveCode> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(AnnualApproveCode::getApproveCode, Function.identity()));

    public static AnnualApproveCode valueOfNumber(int number) {
        return BY_NUMBER.getOrDefault(number, null);
    }
}


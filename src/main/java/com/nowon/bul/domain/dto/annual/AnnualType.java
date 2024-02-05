package com.nowon.bul.domain.dto.annual;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AnnualType {

	ANNUAL(1,"연차","annual"),
	HALF_DAY(2, "반차","halfDay"),
	MATERNITY(3,"출산","maternity"),
	SPOUSE_MATERNITY(4,"배우자출산","spouseMaternity"),
	MENSTRUAL(5,"생리","menstrual"),
	FAMILY_CARE(6,"가족돌봄","familyCare"),
	MEDICAL_LEAVE(7,"약정","medicalLeave");
	
	private final int code;
	private final String typeName;
	private final String typeEnName;

    private static final Map<Integer, AnnualType> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(AnnualType::getCode, Function.identity()));

    public static AnnualType valueOfNumber(int number) {
        return BY_NUMBER.getOrDefault(number, MEDICAL_LEAVE);
    }
}

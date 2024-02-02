package com.nowon.bul.domain.dto.annual;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class AnnualListDTO {

	private long annualNo;
	private int type;
	private long memberNo;
	private String content;
	private LocalDateTime start;
	private LocalDateTime end;
	private LocalDateTime createDate;
	private String halfDayTime;
	private int approve;
	
    // Enum에 대응하는 문자열 반환 메서드
    public String getApprove() {
        return AnnualApproveCode.valueOfNumber(approve).getApproveState();
    }
    public String getType() {
        return AnnualType.valueOfNumber(type).getTypeName();
    }
}

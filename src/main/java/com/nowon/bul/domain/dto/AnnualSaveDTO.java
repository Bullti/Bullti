package com.nowon.bul.domain.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.nowon.bul.domain.dto.annual.AnnualType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class AnnualSaveDTO {

	private long memberNo;
	private LocalDate start;
	private LocalDate end;
	private String halfDayTime;
	private String content;
	private int approve;
	private int type;
	//결제라인
	private String[] line;
	
	public long daysDifference() {
		return ChronoUnit.DAYS.between(start, end);
	}
	public void setType(String type) {
		this.type = AnnualType.valueOf(type).getCode();
	}
	
}

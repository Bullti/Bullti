package com.nowon.bul.domain.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class AnnualSaveDTO {

	private long memberNo;
	private String type;
	private LocalDate start;
	private LocalDate end;
	private String halfDayTime;
	private String head;
	private String content;
	private int approve;
	
	public long daysDifference() {
		return ChronoUnit.DAYS.between(start, end);
	}
}

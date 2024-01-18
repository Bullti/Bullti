package com.nowon.bul.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AnnualSaveDTO {

	private String type;
	private LocalDate start;
	private LocalDate end;
	private String halfDayTime;
	private String head;
	private String content;
}

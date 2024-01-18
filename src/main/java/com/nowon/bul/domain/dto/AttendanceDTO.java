package com.nowon.bul.domain.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendanceDTO {
	private LocalTime goWorkTime;
	private LocalTime leaveWorkTime;
	private int regiType;
	private long memberNo;
}

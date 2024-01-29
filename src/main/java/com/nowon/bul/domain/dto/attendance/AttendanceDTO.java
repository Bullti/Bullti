package com.nowon.bul.domain.dto.attendance;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendanceDTO {
	private LocalDateTime goWorkTime;
	private LocalDateTime leaveWorkTime;
	private int regiType;
	private long memberNo;
}

package com.nowon.bul.mail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DateRangeDTO {
	
    private Date startDate;
    private Date endDate;

}

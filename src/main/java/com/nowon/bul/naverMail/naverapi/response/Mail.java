package com.nowon.bul.naverMail.naverapi.response;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mail {
	private int mailId;
	private int folderId;
	private String status;
	private From from;
	private List<To> to;
	private String subject;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private LocalDateTime receivedTime;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private LocalDateTime sentTime;
	private long size;
	private String securityLevel;
	private boolean useForwarding;
	private int attachCount;
	private boolean isImportant;
	
	public void setReceivedTime(String receivedTime) {
		// DateTimeFormatter를 사용하여 문자열을 ZonedDateTime으로 변환
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(receivedTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // ZonedDateTime을 LocalDateTime으로 변환 (타임존 정보는 무시됨)
        this.receivedTime = zonedDateTime.toLocalDateTime();
		
	}
	public void setSentTime(String sentTime) {
		// DateTimeFormatter를 사용하여 문자열을 ZonedDateTime으로 변환
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(sentTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // ZonedDateTime을 LocalDateTime으로 변환 (타임존 정보는 무시됨)
        this.sentTime = zonedDateTime.toLocalDateTime();
		
	}
}

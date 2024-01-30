package com.nowon.bul.domain.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApprovalWaitListDTO {
	
	private LocalDateTime createdDate;
	
	private String title;
	
	private String docName;
	
	private String state;
}

package com.nowon.bul.domain.dto.approval;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDraftListDTO {
	
	private LocalDateTime createdDate;
	private String title;
	private String docName;
	private String state;
	private Long docNo;
}

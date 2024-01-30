package com.nowon.bul.domain.dto.approval;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ApprovalDTO {
	
	private String title;
	private String content;
	private String[] line;
	
}

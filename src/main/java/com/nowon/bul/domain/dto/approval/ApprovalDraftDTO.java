package com.nowon.bul.domain.dto.approval;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApprovalDraftDTO {

	private String deptName;
	private String rank;
	private String memberName;
	private LocalDateTime createdDate;
	 
	private List<ApprovalLineDTO> approvalLine;
	 
	private String title;
	private String content;
}

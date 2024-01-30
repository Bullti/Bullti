package com.nowon.bul.domain.dto.approval;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApprovalLineDTO {

	private String rank;
	private String name;
	private String result;
	private LocalDateTime approvaledDate;
}

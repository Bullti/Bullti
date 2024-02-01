package com.nowon.bul.domain.dto.annual;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AnnualCancelDTO {

	private long memberNo;
	private long annualNo;
	private int approveCode;
}

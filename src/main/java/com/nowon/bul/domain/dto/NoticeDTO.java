package com.nowon.bul.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
	
	private long boardNo;
	private long memberId;
	private String name;
	private String deptName;
	private String boardTitle;
	private String boardContent;
	private int boardHitcnt;
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;

	
	
}

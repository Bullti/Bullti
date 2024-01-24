package com.nowon.bul.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;


@Getter
//@Builder //
public class NoticeDTO {
	
	private long boardNo;
	private long memberId;
	private String boardTitle;
	private String boardContent;
	private int boardHitcnt;
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;
	private String writer;
	private String rank;
	
	
}

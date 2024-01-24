package com.nowon.bul.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NoticeUpdateDTO {
	
	private long boardNo;
	private String boardTitle;
	private String boardContent;
	
}

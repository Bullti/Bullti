package com.nowon.bul.domain.dto;

import java.util.Optional;

import com.nowon.bul.domain.entity.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoticeSaveDTO {
	
	private String boardTitle;
	private String boardContent;
	private String writer;
	private String dept;


	
	
}

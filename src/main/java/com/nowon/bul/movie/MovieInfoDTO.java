package com.nowon.bul.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoDTO {

	private String movieNm;
	private String posterUrl;
	private String showTm;
	private String openDt;
	private String nations;
}

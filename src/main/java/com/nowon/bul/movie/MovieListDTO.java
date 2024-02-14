package com.nowon.bul.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieListDTO {
	
	private String rank;
	private String movieNm;
	private String openDt;
	private String movieCd;
}

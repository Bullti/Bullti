package com.nowon.bul.naverMail.naverapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class From {
	
	private String name;
	private String email;

}

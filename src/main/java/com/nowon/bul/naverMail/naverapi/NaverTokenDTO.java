package com.nowon.bul.naverMail.naverapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverTokenDTO {
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expires_in;
	private String scope;
}

package com.nowon.bul.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response{
	//@JsonProperty("header")
	private Header header;
	//@JsonProperty("body")
	private Body body;
}
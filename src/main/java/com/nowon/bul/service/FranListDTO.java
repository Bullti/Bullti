package com.nowon.bul.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FranListDTO {
	
	private Long id;
	
	private String name;
	private String address;
	private String ph;
	
	private LocalDate createdAt;
	private LocalDate closedAt;
}

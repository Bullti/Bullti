package com.nowon.bul.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

@Configuration
public class MovieConfig {

	private String key = "aec55f85728e58f0d2b857f15594ad5a";
	
	@Bean
	KobisOpenAPIRestService kobisOpenAPIRestService() {
		return new KobisOpenAPIRestService(key); 
	}
	
}

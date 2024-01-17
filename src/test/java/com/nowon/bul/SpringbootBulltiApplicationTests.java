package com.nowon.bul;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.department.DeService;

@SpringBootTest
class SpringbootBulltiApplicationTests {

	@Autowired
	DeService deService;
	
	//@Test
	void deSave() {
		DeEntity.builder()
				.deptId(1234)
				.deptName("영업부")
				.build();
		
	}

}

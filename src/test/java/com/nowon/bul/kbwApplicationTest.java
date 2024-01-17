package com.nowon.bul;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.bul.mybatis.mapper.AttendanceMapper;

@SpringBootTest
class kbwApplicationTest {

	@Autowired
	private AttendanceMapper mapper;

	@Test
	void workinchecktest() {
		for(int i=0; i<3; i++)
			mapper.workIn(5959L);
		
	}
}

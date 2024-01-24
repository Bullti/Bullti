package com.nowon.bul;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.bul.domain.dto.attendance.AttendanceCheckDTO;
import com.nowon.bul.mybatis.mapper.AttendanceMapper;

@SpringBootTest
class kbwApplicationTest {

	@Autowired
	private AttendanceMapper mapper;

	//@Test
	void workinchecktest() {
		AttendanceCheckDTO dto = new AttendanceCheckDTO();
		dto.setMemberNo(6L);
		dto.setRegiType(1);
		for(int i=0; i<3; i++)
			mapper.workIn(dto);
	}
}

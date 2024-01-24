package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nowon.bul.domain.dto.AttendanceDTO;
import com.nowon.bul.domain.dto.attendance.AttendanceCheckDTO;

@Mapper
public interface AttendanceMapper {

	void workIn(AttendanceCheckDTO dto);

	List<AttendanceDTO> find(Long memberNo);
	
}

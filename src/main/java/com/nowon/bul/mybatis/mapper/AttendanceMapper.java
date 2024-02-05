package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nowon.bul.domain.dto.attendance.AttendanceCheckDTO;
import com.nowon.bul.domain.dto.attendance.AttendanceDTO;

@Mapper
public interface AttendanceMapper {

	void workIn(AttendanceCheckDTO dto);

	List<AttendanceDTO> find(@Param("memberNo") long memberNo
			,@Param("limit") int limit,@Param("offset") int offset);

	int countAllById(long memberNo);

	AttendanceDTO findStatus(long MemberNo);

}

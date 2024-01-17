package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nowon.bul.domain.dto.AttendanceDTO;

@Mapper
public interface AttendanceMapper {

	void workIn(Long memberNo);

	List<AttendanceDTO> find(Long memberNo);
	
}

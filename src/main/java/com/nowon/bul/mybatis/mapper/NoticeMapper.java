package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nowon.bul.domain.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	List<NoticeDTO> findAll();
	
}

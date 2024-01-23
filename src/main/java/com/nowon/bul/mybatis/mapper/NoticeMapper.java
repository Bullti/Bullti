package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.nowon.bul.domain.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	List<NoticeDTO> findAll();

	List<NoticeDTO> findAll(RowBounds rowBounds);

	List<NoticeDTO> findAllLimit(@Param("offset") int offset,@Param("limit") int limit);

	int countAll();
	
	
	
}

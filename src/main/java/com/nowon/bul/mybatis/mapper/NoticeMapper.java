package com.nowon.bul.mybatis.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.dto.NoticeSaveDTO;

@Mapper
public interface NoticeMapper {
	
	//List<NoticeDTO> findAll();

	//List<NoticeDTO> findAll(RowBounds rowBounds);

	List<NoticeDTO> findAllLimit(@Param("offset") int offset,@Param("limit") int limit);

	int countAll();

	void save(NoticeSaveDTO dto);

	Optional<NoticeDTO> findById(long boardNo);

	int deleteById(long boardNo);
	
	
	
}

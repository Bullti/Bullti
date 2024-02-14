package com.nowon.bul.mybatis.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.domain.dto.NoticeUpdateDTO;
import com.nowon.bul.domain.entity.member.Member;

@Mapper
public interface NoticeMapper {
	
	//List<NoticeDTO> findAll();
	
	List<NoticeDTO> findAll(@Param("search") String search, @Param("offset") int offset,@Param("limit") int limit);

	//List<NoticeDTO> findAll(RowBounds rowBounds);

	List<NoticeDTO> findAllLimit(@Param("offset") int offset,@Param("limit") int limit);

	int countAll();
	
	int countAllSearch(String search);

	void save(NoticeSaveDTO dto);

	Optional<NoticeDTO> findById(long boardNo);

	int deleteById(long boardNo);

	void updateTitleOrContent(NoticeUpdateDTO dto);
	
	NoticeSaveDTO findNameById(long memberId);
	
	NoticeDTO findNameByIdd(long memberId);
	
	String findDeptNameById(long deptId);

	void increaseHitCount(long boardNo);
	
}

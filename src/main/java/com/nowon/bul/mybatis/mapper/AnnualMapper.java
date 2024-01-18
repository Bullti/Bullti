package com.nowon.bul.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nowon.bul.domain.dto.AnnualSaveDTO;

@Mapper
public interface AnnualMapper {

	void annuSave(AnnualSaveDTO dto,long memberNo);

	
}

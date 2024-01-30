package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.domain.dto.annual.AnnualListDTO;

@Mapper
public interface AnnualMapper {

	void annuSave(AnnualSaveDTO dto);

	List<AnnualListDTO> findByMemberNo(long memberNo);

	void cancelByNo(long annualNo);

	
}

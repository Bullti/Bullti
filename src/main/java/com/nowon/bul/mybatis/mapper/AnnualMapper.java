package com.nowon.bul.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nowon.bul.domain.dto.AnnualSaveDTO;
import com.nowon.bul.domain.dto.annual.AnnualCancelDTO;
import com.nowon.bul.domain.dto.annual.AnnualListDTO;

@Mapper
public interface AnnualMapper {

	void annuSave(AnnualSaveDTO dto);

	List<AnnualListDTO> findByMemberNo(long memberNo);

	void cancelByNo(@Param("memberNo") long memberNo,@Param("annualNo") long annualNo);

	void cancelByNo(AnnualCancelDTO dto);

	
}

package com.nowon.bul.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnualApprovalMapper {

	String[] findAll();

}

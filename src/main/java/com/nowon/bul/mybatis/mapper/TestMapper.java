package com.nowon.bul.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 매퍼스캔으로 등록하는 방법도 있음
public interface TestMapper {
	
	int cont();
}

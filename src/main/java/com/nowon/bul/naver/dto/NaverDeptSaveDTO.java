package com.nowon.bul.naver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class NaverDeptSaveDTO {
	
	@JsonIgnore
    private String deptExternalKey;              // "dept0000",
    private String name;               		     // "부서이름",
    private String parentDeptExternalKey;        //  null,
    private String dispOrd;          	     // "1.0",
}

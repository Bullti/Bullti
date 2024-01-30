package com.nowon.bul.domain.dto;

import com.nowon.bul.domain.entity.fran.FranEntity;
import com.nowon.bul.domain.entity.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class FranEditDTO {

    private Long id;
    private String name;
    private String address;
    private String address2;
    private String ph;

    // Owner의 이름을 추가
    private String ownerName;

    public FranEntity toEntity() {
        return FranEntity.builder()
                .id(id)
                .name(name)
                .address(address)
                .address2(address2)
                .ph(ph)
                .owner(Member.builder().name(ownerName).build())  // Owner 정보 추가
                .build();
    }
}
package com.nowon.bul.domain.entity.fran;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.nowon.bul.domain.dto.FranEditDTO;
import com.nowon.bul.domain.dto.FranListDTO;
import com.nowon.bul.domain.dto.FranUpdateDTO;
import com.nowon.bul.domain.entity.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "franchise")
public class FranEntity {
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String address2;
    private String ph;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;
    
    @CreationTimestamp
    @Column(columnDefinition = "timestamp(6) null")
    private LocalDate createdAt;
    private LocalDate closedAt;

    public FranListDTO toFranListDTO() {
        return FranListDTO.builder()
                .id(id).name(name).address(address).address2(address2).ph(ph).createdAt(createdAt).closedAt(closedAt)
                .build();
    }
    
    public void closeFran() {
    	this.closedAt = LocalDate.now();
    }

    public FranEditDTO toFranEditDTO() {
        return FranEditDTO.builder()
                .id(id)
                .name(name)
                .address(address)
                .address2(address2)
                .ph(ph)
                .ownerName(owner.getName())  // Owner의 이름 추가
                .build();
    }
    
	public void updateFran(FranUpdateDTO dto) {
        // 필요한 업데이트 로직을 구현
        this.address = dto.getAddress();
        this.address2 = dto.getAddress2();
        this.ph = dto.getPh();
        // 필요한 필드들을 업데이트
    }
}
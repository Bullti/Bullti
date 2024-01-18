package com.nowon.bul.domain.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.nowon.bul.service.FranListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FranEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String address;
    private String address2;
    private String ph;

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
    
}
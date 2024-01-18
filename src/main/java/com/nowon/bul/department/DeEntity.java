package com.nowon.bul.department;

import java.util.List;
import java.util.Optional;

import com.nowon.bul.domain.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "child")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
@Entity
public class DeEntity {

    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column
    private String deptName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DeEntity.class)
    @JoinColumn(name = "parent_dept_id")
    private DeEntity parent;
    
    @OneToMany(mappedBy = "parent")
    private List<DeEntity> 	child;
    
    @OneToOne(mappedBy = "dept")
    private Member member;
    
    
    public DeListDTO toListDTO() {
		return DeListDTO.builder()
				.deptId(deptId)
				.deptName(deptName)
				.build();
    }



}

package com.nowon.bul.department;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DeEntity {

    @Id
    @Column(name = "dept_id")
    private int deptId;

    @Column
    private String deptName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DeEntity.class)
    @JoinColumn(name = "parent_dept_id", referencedColumnName = "dept_id", insertable = false, updatable = false)
    private DeEntity parent;
    
    @OneToMany(mappedBy = "parent")
    private List<DeEntity> 	child;
    
    
    
    public DeListDTO toListDTO() {
		return DeListDTO.builder()
				.deptId(deptId)
				.deptName(deptName)
				.build();
    }
}

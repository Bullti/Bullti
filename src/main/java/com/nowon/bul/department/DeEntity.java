package com.nowon.bul.department;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.nowon.bul.domain.dto.approval.ApprovalDeptList;
import com.nowon.bul.domain.entity.member.Member;

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
    
    @OneToMany(mappedBy = "dept")
    private List<Member> member;
    
    
    public DeListDTO toListDTO() {
    	
    	DeListDTO deListdto = null;
    	
    	if(this.parent==null) {
    		deListdto = DeListDTO.builder()
    				.deptId(deptId)
    				.deptName(deptName)
    				.build();
    	}else {
    		deListdto = DeListDTO.builder()
    				.deptId(deptId)
    				.deptName(deptName)
    				.parentId(parent.getDeptId())
    				.build();
    	}
		return deListdto;
    }
    
    
    
    @Transactional
    public ApprovalDeptList toApprovalList() {
    	
    	ApprovalDeptList dto = null;
    	
    	if(this.parent==null) {
    		dto = ApprovalDeptList.builder()
    				.deptId(deptId)
    				.deptName(deptName)
    				.child(child.stream()
    						.map(DeEntity::toApprovalList)
    						.collect(Collectors.toList()))
    				.build();
    	}else {
    		dto = ApprovalDeptList.builder()
    				.deptId(deptId)
    				.deptName(deptName)
    				.child(child.stream()
    						.map(DeEntity::toApprovalList)
    						.collect(Collectors.toList()))
    				.parentId(parent.getDeptId())
    				.build();
    	}
		return dto;
    }



}

package com.nowon.bul.domain.entity.dept;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<DeptEntity, Integer> {

    Optional<DeptEntity> findByDeptName(String name);

    Optional<DeptEntity> DeptName(String name);

    void deleteByDeptName(String name);
    
}



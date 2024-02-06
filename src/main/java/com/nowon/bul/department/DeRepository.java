package com.nowon.bul.department;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeRepository extends JpaRepository<DeEntity, Integer> {

    Optional<DeEntity> findByDeptName(String name);

    Optional<DeEntity> DeptName(String name);

    void deleteByDeptName(String name);
    
}



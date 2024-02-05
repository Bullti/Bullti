package com.nowon.bul.chatbot;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpEntityRepository extends JpaRepository<EmpEntity, Long>{

	List<EmpEntity> findByName(String name);

}

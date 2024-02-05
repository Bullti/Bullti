package com.nowon.bul.chatbot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEntityRepository extends JpaRepository<DeptEntity, Long> {

	Optional<DeptEntity> findByName(String string);

}

package com.nowon.bul.chatbot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatBotIntentionRepository extends JpaRepository<ChatBotIntention, Long>{

	Optional<ChatBotIntention> findByName(String token);

	Optional<ChatBotIntention> findByNameAndUpper(String token, ChatBotIntention upper);

}

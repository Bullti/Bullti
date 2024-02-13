package com.nowon.bul.chatbot.rabbitmq;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.nowon.bul.chatbot.komoran.KomoranService;
import com.nowon.bul.domain.entity.komoran.Answer;

import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Receiver {
	
	private final SimpMessagingTemplate smt;
	private final Komoran komoran;
	private final KomoranService komoranService;

	
	//RabbitTemplate Template 에서 전달한 메세지가 전송된다.
	public void receiveMessage(Question dto) {
		System.out.println("8080>>>>>>>"+dto);
		String komoranResult = null;
		//의도분석 -> 응답메세지 작성
		Answer answer=null;
		try {
			komoranResult=komoranService.nlpAnalyze(dto.getContent()).getAnswer().getContent();
		System.out.println("코모란리턴>>>>>>>>"+komoranResult);
		} catch(Exception e) {
			System.out.println(e);
		}
//		if(dto.getContent().equals("인사말")) {
//			answer=Answer.builder().content(dto.getName()+"님! 안녕하세요!").build();
//		} else {
//			//komoran에 질문내용을 보내서 의도분석
//			
//			KomoranResult analyzeResultList = komoran.analyze(dto.getContent());
//
//		    System.out.println(analyzeResultList.getPlainText());
//		    
//		    List<Token> tokenList = analyzeResultList.getTokenList();
//		    for (Token token : tokenList) {
//		        System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
//		    }
//		    
//		    answer=Answer.builder().content(dto.getName()+"님! 안녕하세요!").build();
//		}
		answer=Answer.builder().content(komoranResult).build();
		smt.convertAndSend("/topic/question/"+dto.getKey(), answer);
	}
}

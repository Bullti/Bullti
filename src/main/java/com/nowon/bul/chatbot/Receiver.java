package com.nowon.bul.chatbot;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nowon.bul.chatbot.Answer;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Receiver {
	
	private final SimpMessagingTemplate smt;
	
	private final KomoranService komoranService;
	private final TemplateEngine templateEngine; // Inject Thymeleaf template engine
	//RabbitTemplate template 에서 전달란 메세지가 전송된다.
	public void receiveMessage(Question dto) {
		System.out.println(">>>>"+dto);
		
		//komoran을 사용해서
		//의도분석->응답메세지 작성
		MessageDTO msg=komoranService.nlpAnalyze(dto.getContent());
	        
		Context thymeleafContext = new Context();
		thymeleafContext.setVariable("msg", msg);
		// Process the Thymeleaf template
		String htmlResponse = templateEngine.process("layout/bot-message", thymeleafContext);
		//응답메세지 보내기
		smt.convertAndSend("/topic/question/"+dto.getKey(), htmlResponse);
		
	}

}

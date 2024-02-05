package com.nowon.bul;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.bul.chatbot.Answer;
import com.nowon.bul.chatbot.AnswerRepository;
import com.nowon.bul.chatbot.ChatBotIntention;
import com.nowon.bul.chatbot.ChatBotIntentionRepository;
import com.nowon.bul.chatbot.DeptEntity;
import com.nowon.bul.chatbot.DeptEntityRepository;
import com.nowon.bul.chatbot.EmpEntity;
import com.nowon.bul.chatbot.EmpEntityRepository;

@SpringBootTest
class Springboot3ChabotApplicationTests {

	@Autowired
	DeptEntityRepository deptEntityRepository;
	//@Test
	void 부서등록() {
		//deptEntityRepository.save(DeptEntity.builder().name("그린").build());
		deptEntityRepository.save(DeptEntity.builder().name("불티치킨")
				.upper(deptEntityRepository.findByName("그린").orElseThrow())
				.build());
	}
	
	@Autowired
	EmpEntityRepository empEntityRepository;
	//@Test
	void 사원등록() {
		//empEntityRepository.save(EmpEntity.builder().name("조재청").phone("1111").build());
		String[] names= {"김병옥","최현종","인성빈","최영진","이예슬"};
		String[] phones= {"2001","2002","2003","2004","2005"};
		IntStream.range(0, 5).forEach(i->{
			empEntityRepository.save(EmpEntity.builder().name(names[i]).phone(phones[i])
					.dept(deptEntityRepository.findByName("불티치킨").orElse(null))
					.build());
		});
	}
	
	//@Test
	void 사원등록2() {
		//empEntityRepository.save(EmpEntity.builder().name("조재청").phone("1111").build());
		String[] names= {"고형철","조영진","한재훈","남원호","조영훈"};
		String[] phones= {"3001","3002","3003","3004","3005"};
		IntStream.range(0, 5).forEach(i->{
			empEntityRepository.save(EmpEntity.builder().name(names[i]).phone(phones[i])
					.dept(deptEntityRepository.findByName("코드블룸").orElse(null))
					.build());
		});
	}
	
	@Autowired
	ChatBotIntentionRepository chatBotIntentionRepository;
	@Autowired
	AnswerRepository answerRepository;
	//@Test
	void 의도등록() {
		chatBotIntentionRepository.save(ChatBotIntention.builder()
				.name("안녕")
				.answer(answerRepository.save(Answer.builder()
						.keyword("안녕")
						.content("안녕하세요</br> 저는 조청BOT입니다.")
						.build()))
				.build());
	}
	
	//@Test
	void 의도등록2() {
		chatBotIntentionRepository.save(ChatBotIntention.builder()
				.name("안녕하세요")
				.answer(answerRepository.findByKeyword("안녕").get())
				.build());
	}
	
	//@Test
	void 의도기타() {
		chatBotIntentionRepository.save(ChatBotIntention.builder()
				.name("기타")
				.answer(answerRepository.save(Answer.builder()
						.keyword("기타")
						.content("질문이 정확하지 않습니다.</br> 정확하게 입력 해 주세요")
						.build()))
				.build());
	}
	
	//@Test
	void 전화번호() {
		chatBotIntentionRepository.save(ChatBotIntention.builder()
				.name("번호")
				.answer(answerRepository.save(Answer.builder()
						.keyword("전화번호")
						.content("전화번호 안내해드립니다.</br>")
						.build()))
				.build());
	}
	//@Test
	void 전화번호2() {
		chatBotIntentionRepository.save(ChatBotIntention.builder()
				.name("전화번호") //"전화", "전번","전화번호"
				.answer(answerRepository.findByKeyword("전화번호").get())
				.build());
	}
	
	//@Test
	void 인사말() {
		chatBotIntentionRepository.save(ChatBotIntention.builder()
				.name("인사말") //
				.answer(answerRepository.save(Answer.builder()
						.keyword("인사말")
						.content("안녕하세요! 저는 N조청 봇이에요. 궁금한 점을 물어보세요!")
						.build()))
				.build());
	}

}

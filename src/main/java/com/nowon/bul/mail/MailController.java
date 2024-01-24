package com.nowon.bul.mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.jboss.jandex.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	
	private final MailSender mailSender;
	private final MailReader mailReader;

	@GetMapping("/mail")
	public String mailSend() {
		
		mailSender.sendMail();
		
		return "mail/mail";
	}
	
	@GetMapping("/mail/list")
	public String mailRead() {
		// 메일을 읽어오는 로직을 직접 호출
		try {
			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-24");
			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-24");
			String saveDirectory = "E:\\kdt2023";
			mailReader.setSaveDirectory(saveDirectory);
			mailReader.receiveMailAttachedFile("dptmf921008@gmail.com", "nabuzgdoguboxeph", startDate, endDate);
		} catch (ParseException | MessagingException e) {
			e.printStackTrace();
			// 오류 처리 로직 추가
		}
		
		return "mail/mailList";
	}
	
	
}

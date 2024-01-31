package com.nowon.bul.mail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {

	private final MailSender mailSender;
	private final MailReader mailReader;

	@GetMapping("/mail")
	public String mailSend() {

		// mailSender.sendMail();

		return "mail/mail";
	}

	@GetMapping("/mail/list")
	public String mailRead2() {

		return "mail/mailList";
	}

	@ResponseBody
	@PostMapping("/mail/list")
	public List<MailDTO> mailRead(@RequestBody DateRangeDTO dateRange, Model model) throws MessagingException {
		// 메일을 읽어오는 로직을 직접 호출

		String userName = "dptmf921008@gmail.com";
		String password = "nabuzgdoguboxeph";

		LocalDate nowDate = LocalDate.now();

		// Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-24");
		// Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-24");

		// 현재시간
		//Date endDate = Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		// 하루 전
		LocalDate yesterDate = nowDate.minusDays(1);
		//Date startDate = Date.from(yesterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<MailDTO> mailList = mailReader.getMailList(userName, password, dateRange.getStartDate(), dateRange.getEndDate(), model);
		
		return mailList;
	}
	
	

	

}

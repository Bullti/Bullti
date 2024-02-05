package com.nowon.bul.mail;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {

	private final MailSender mailSender;
	private final MailReader mailReader;
	private final MailService mailService;
	

	  @PostMapping("/sendMail")
	   public ResponseEntity<String> sendMail(@RequestBody MailData mailData, Authentication authentication) {
	       try {
	           String toAddress = mailData.getToAddress();
	           String subject = mailData.getSubject();
	           String content = mailData.getContent();

	           // MailSender를 통해 이메일 전송
	           mailSender.sendMail(authentication, toAddress, subject, content);
	           
	            return ResponseEntity.ok("이메일 전송 성공");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 전송 중 오류 발생: " + e.getMessage());
	        }
	    }
	   
	   
	@GetMapping("/mail/list")
	public String mailRead2() {
		return "mail/mailList";
	}

	
	
	
	/********************비동기
	 * @throws IOException *****************************/
	
	@ResponseBody
	@PostMapping("/mail/list")
	public List<MailDTO> mailRead(@RequestBody DateRangeDTO dateRange, Model model, Authentication authentication) throws MessagingException, IOException {
		// 메일을 읽어오는 로직을 직접 호출
		MyUser myuser = (MyUser) authentication.getPrincipal();
		MemberEmail memberEmail = mailService.findById(myuser.getMemberNo());
		
		String userName = memberEmail.getEmail();
		String password = memberEmail.getPassword();

		LocalDate nowDate = LocalDate.now();

		// 현재시간
		//Date endDate = Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		// 하루 전
		LocalDate yesterDate = nowDate.minusDays(1);
		//Date startDate = Date.from(yesterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<MailDTO> mailList = mailReader.getMailList(userName, password, dateRange.getStartDate(), dateRange.getEndDate(), model);

		return mailList;
	}
	
	
	
	

}

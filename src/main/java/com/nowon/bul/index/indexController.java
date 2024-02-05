package com.nowon.bul.index;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.mail.MailController;
import com.nowon.bul.mail.MailService;
import com.nowon.bul.mail.MemberEmail;
import com.nowon.bul.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {

	private final NoticeService noticeService;
	private final MailService mailService;
	
	
	@GetMapping("/")
	public String indexNotice(Authentication authentication, Model model) {
		MyUser myuser = (MyUser) authentication.getPrincipal();
		MemberEmail memberEmail = mailService.findById(myuser.getMemberNo());
//		
//		//공지사항 1번 페이지
		int page = 1;
		int mailCount=10;
		
		noticeService.listProcess(page, model);
		if(memberEmail!=null) mailService.listProcess(memberEmail, mailCount, model);
	
		
		return "index";
	}
}

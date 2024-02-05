package com.nowon.bul.mail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.entity.member.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailServiceProcess implements MailService {

	private final MailRepository mailRepository;
	private final MailReader mailReader;
	

	@Override
	public MemberEmail findById(Long memberno) {
		return mailRepository.findById(memberno).orElseThrow();
	}

	@Override
	public void listProcess(MemberEmail memberEmail, int mailCount, Model model) {
		// 현재시간		
		LocalDate nowDate = LocalDate.now();
		// LocalDate -> Date
		Date endDate = Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		// 시작시간
		LocalDate startLocalDate = nowDate.minusDays(10);
		// LocalDate -> Date
		Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println(startDate);
		System.out.println(endDate);
		mailReader.getMailList(memberEmail.getEmail(), memberEmail.getPassword(), startDate, endDate, mailCount ,model);
		
		
	}

}

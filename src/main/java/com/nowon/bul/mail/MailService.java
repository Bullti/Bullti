package com.nowon.bul.mail;

import org.springframework.ui.Model;

public interface MailService {

	MemberEmail findById(Long memberno);

	//인덱스 페이지 메일 가져오기
	void listProcess(MemberEmail memberEmail, int mailCount, Model model);

	
}

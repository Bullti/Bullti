package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nowon.bul.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
public class NoticeController {
	

	private final NoticeService service;
	
	//공지사항 작성 페이지 이동
	@GetMapping("/members/notice-post")
	public String notice() {
		return "stock/notice-post";
	}
	
	//게시글 리스트
	@GetMapping("/members/notice")
	public String notice_post(Model model) {
		
		service.listProcess(model);
		
		return "stock/notice";
	}
	
	

	

}

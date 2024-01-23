package com.nowon.bul.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.mybatis.mapper.NoticeMapper;
import com.nowon.bul.service.NoticeService;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class NoticeProcess implements NoticeService{

	private final NoticeMapper noticeMapper;
	
	@Override
	public void listProcess(Model model) {
		
		model.addAttribute("list", noticeMapper.findAll());
		noticeMapper.findAll();
	}

	@Override
	public void listProcess(int page, Model model) {
		// TODO Auto-generated method stub
		
	}
	
	
}

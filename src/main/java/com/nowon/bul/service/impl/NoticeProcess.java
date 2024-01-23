package com.nowon.bul.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.mybatis.mapper.NoticeMapper;
import com.nowon.bul.service.NoticeService;
import com.nowon.bul.utils.PageData;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class NoticeProcess implements NoticeService{

	private final NoticeMapper noticeMapper;
	
	@Override
	public void listProcess(Model model) {
		
		int limit=10;
		int offset=0;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NoticeDTO> result = noticeMapper.findAll(rowBounds);
		model.addAttribute("list", result);
		
	}

	@Override
	public void listProcess(int page, Model model) {
		
		int offset=(page-1)*10;
		int limit = 10;
		
		List<NoticeDTO> result = noticeMapper.findAllLimit(offset,limit);
		model.addAttribute("list",result);
		
		int rowCount = noticeMapper.countAll();
		
		model.addAttribute("pu",PageData.create(page, limit, rowCount, 5));

		
	}
	
	
}

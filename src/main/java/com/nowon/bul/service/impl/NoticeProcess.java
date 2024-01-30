package com.nowon.bul.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.department.DeRepository;
import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.domain.dto.NoticeUpdateDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.mybatis.mapper.NoticeMapper;
import com.nowon.bul.service.NoticeService;
import com.nowon.bul.utils.PageData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeProcess implements NoticeService{

	private final NoticeMapper noticeMapper;
	private final MemberRepository memberRepository;
	private final DeRepository deRepository;
	
	
	@Override
	public void listProcess(Model model) {
		
		//int limit=10;
		//int offset=0;
		
		//RowBounds rowBounds = new RowBounds(offset, limit);
		//List<NoticeDTO> result = noticeMapper.findAll(rowBounds);
		//model.addAttribute("list", result);
		
	}

	@Override
	public void listProcess(int page, Model model) {
		int limit = 10;
		int offset=(page-1)*limit;
		
		
		List<NoticeDTO> result = noticeMapper.findAllLimit(offset,limit);
		model.addAttribute("list",result);
		
		int rowCount = noticeMapper.countAll();
		
		model.addAttribute("pu",PageData.create(page, limit, rowCount, 5));

		
	}

	@Override
	public void saveProcess(Authentication auth,NoticeSaveDTO dto) {
		
		String author=auth.getName();
		Optional<Member> member = memberRepository.findById(author);
		
			
			Member memb = member.get();
			
	}

	@Override
	public void detailProcess(long boardNo, Model model) {
		
		NoticeDTO result = noticeMapper.findById(boardNo).orElseThrow();
		
		model.addAttribute("detail",result);
		
	}

	@Override
	public void deleteProcess(long boardNo) {
		
		int result = noticeMapper.deleteById(boardNo);
		
		log.debug(result+"개의 게시글이 삭제 됨 : "+boardNo);
		
	}

	@Override
	public void updateProcess(NoticeUpdateDTO dto) {
		
		noticeMapper.updateTitleOrContent(dto);
		
	}
	
	
}

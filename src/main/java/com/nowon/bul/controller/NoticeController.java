package com.nowon.bul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
public class NoticeController {
	

	private final NoticeService service;
	
	//공지사항 작성 페이지 이동
	@GetMapping("/members/notice-post")
	public String notice(Model model) {
		model.addAttribute("boardTitle","");
		model.addAttribute("boardContent", "");
		return "stock/notice-post";
	}
	
	//게시글 리스트
	@GetMapping("/members/notice")
	public String notice_post(
			@RequestParam(name="page",defaultValue = "1") int page,
			Model model) {
		
		service.listProcess(page,model);
		
		return "stock/notice";
	}
	
	//게시글 저장
	@PostMapping("/members/notice-post")	
	public String save(NoticeSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/members/notice";
		
	}
	
	//상세페이지 조회
	@GetMapping("members/notice/{boardNo}")
	public String detail(@PathVariable(name = "boardNo") long boardNo, Model model) {
		
		service.detailProcess(boardNo, model);
		
		return "stock/notice-detail";
	}
	
	//삭제 처리
	@DeleteMapping("/members/notice/{boardNo}")
	public String delete(@PathVariable(name = "boardNo") long boardNo) {
		
		service.deleteProcess(boardNo);
		
		return "redirect:/members/notice";
		
	}
	
}

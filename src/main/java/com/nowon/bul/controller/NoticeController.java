package com.nowon.bul.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.domain.dto.NoticeUpdateDTO;
import com.nowon.bul.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
public class NoticeController {
	

	private final NoticeService service;
	
	//공지사항 작성 페이지 이동
	@GetMapping("/members/notice-post")
	public String notice(Authentication auth, Model model) {
		
		return service.getIndividual(auth,model);
	}
	
	//게시글 리스트
	//@GetMapping("/members/notice-page")
	//public String notice_post() {
	//	return "stock/notice";
	//}
	
	//게시글 리스트
	@ResponseBody
	@GetMapping("/members/notice")
	public ModelAndView notice_post(
			@RequestParam(name="page",defaultValue = "1") int page,
			@RequestParam(name = "search", defaultValue = "", required = false) String search
			) {
		
		
		
		return service.listProcess(page,search);
	}
	
	//게시글 저장
	@PostMapping("/members/notice-post")	
	public String save(Authentication auth,NoticeSaveDTO dto ) {
		

		
		return service.saveProcess(auth,dto);
		
	}
	
	//상세페이지 조회
	@GetMapping("members/notice/{boardNo}")
	public String detail(@PathVariable(name = "boardNo") long boardNo, Model model) {
		
		service.getNoticeDetail(boardNo);
		
		service.detailProcess(boardNo, model);
		
		return "stock/notice-detail";
	}
	
	//삭제 처리
	@DeleteMapping("/members/notice/{boardNo}")
	public String delete(@PathVariable(name = "boardNo") long boardNo) {
		
		service.deleteProcess(boardNo);
		
		return "redirect:/members/notice";
		
	}
	
	//수정 처리
	@PutMapping("/members/notice/{boardNo}")
	public String update(NoticeUpdateDTO dto) {
		
		service.updateProcess(dto);
		
		return "redirect:/members/notice/{boardNo}";
	}
	
	
	
}

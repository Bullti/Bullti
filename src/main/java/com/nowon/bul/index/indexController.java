package com.nowon.bul.index;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowon.bul.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {

	private final NoticeService service;
	
	@GetMapping("/")
	public String indexNotice(
			@RequestParam(name="page",defaultValue = "1") int page,
			Model model) {
		  if (page < 1) {
		        // 예외 처리 또는 디폴트 페이지 설정
		        page = 1;
		    }

		    service.listProcess(page, model);
		    return "index";

	}
}

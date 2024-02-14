package com.nowon.bul.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.domain.dto.NoticeUpdateDTO;


public interface NoticeService {


	void listProcess(int page, Model model);

	String saveProcess(Authentication auth,NoticeSaveDTO dto);

	void detailProcess(long boardNo, Model model);

	void deleteProcess(long boardNo);

	void updateProcess(NoticeUpdateDTO dto);

	String getIndividual(Authentication auth, Model model);

	ModelAndView listProcess(int page, String search);

	NoticeDTO getNoticeDetail(long boardNo);

}

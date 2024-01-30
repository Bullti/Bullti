package com.nowon.bul.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.domain.dto.NoticeUpdateDTO;


public interface NoticeService {

	void listProcess(Model model);

	void listProcess(int page, Model model);

	void saveProcess(Authentication auth,NoticeSaveDTO dto);

	void detailProcess(long boardNo, Model model);

	void deleteProcess(long boardNo);

	void updateProcess(NoticeUpdateDTO dto);

}

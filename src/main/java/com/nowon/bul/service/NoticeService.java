package com.nowon.bul.service;

import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.NoticeSaveDTO;


public interface NoticeService {

	void listProcess(Model model);

	void listProcess(int page, Model model);

	void saveProcess(NoticeSaveDTO dto);

}

package com.nowon.bul.service;

import org.springframework.ui.Model;


public interface NoticeService {

	void listProcess(Model model);

	void listProcess(int page, Model model);

}

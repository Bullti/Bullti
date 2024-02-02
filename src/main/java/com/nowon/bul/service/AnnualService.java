package com.nowon.bul.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.nowon.bul.domain.dto.AnnualSaveDTO;

public interface AnnualService {

	void save(AnnualSaveDTO dto, Authentication auth);

	void list(Authentication auth, Model model);

	void cancel(Authentication auth, long annualNo);


}

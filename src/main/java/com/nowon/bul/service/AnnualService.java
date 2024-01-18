package com.nowon.bul.service;

import org.springframework.security.core.Authentication;

import com.nowon.bul.domain.dto.AnnualSaveDTO;

public interface AnnualService {

	void save(AnnualSaveDTO dto, Authentication auth);

}

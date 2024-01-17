package com.nowon.bul.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

public interface AttendanceService {

	void workIn(Authentication auth);

	void find(Authentication auth, Model model);

}

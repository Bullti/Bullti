package com.nowon.bul.service;

import org.springframework.security.core.Authentication;

public interface AttendanceService {

	void checkIn(Authentication auth);

}

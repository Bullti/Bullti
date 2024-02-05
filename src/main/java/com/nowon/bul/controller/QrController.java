package com.nowon.bul.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nowon.bul.service.GoogleQrService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QrController {

	private final GoogleQrService qrService;
	
	@GetMapping("/emp/atte/cqr")
	public ResponseEntity<byte[]> attendanceQr() {
		return qrService.attendanceQr();
	}
}

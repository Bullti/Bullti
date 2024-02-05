package com.nowon.bul.service;

import org.springframework.http.ResponseEntity;

import com.google.zxing.WriterException;

public interface GoogleQrService {

	ResponseEntity<byte[]> attendanceQr();

}

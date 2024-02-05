package com.nowon.bul.service.impl;

import java.io.ByteArrayOutputStream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.nowon.bul.service.GoogleQrService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class GoogleQrProcess implements GoogleQrService {

	@Override
	public ResponseEntity<byte[]> attendanceQr() {
		int width = 200;
		int height = 200;
		String uri = "/emp/atte/chec";
		String serverUrl = serverUrl();
		String url = serverUrl + uri;
		BitMatrix encode = null;
		try {
			encode = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(encode, "PNG", out);
			return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String serverUrl() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	}
}

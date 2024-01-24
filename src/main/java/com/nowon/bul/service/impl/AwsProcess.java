package com.nowon.bul.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.bul.service.AwsService;
import com.nowon.bul.utils.S3FileUploadUtilV3;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AwsProcess implements AwsService{

	private final S3FileUploadUtilV3 s3FileUploadUtilV3; 
	
	//이미지 임시저장소 저장
	@Override
	public Map<String, String> s3fileTempUpload(MultipartFile img) {
		return s3FileUploadUtilV3.s3TempUpload(img);
	}

	@Override
	public String s3fileTemptoSrc(String newName) {
		return s3FileUploadUtilV3.s3TempToSrc(newName);
	}


}

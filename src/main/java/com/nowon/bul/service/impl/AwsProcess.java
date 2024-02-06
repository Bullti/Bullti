package com.nowon.bul.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.bul.service.AwsService;
import com.nowon.bul.utils.S3FileUploadUtilV3;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AwsProcess implements AwsService{

	private final S3FileUploadUtilV3 s3FileUploadUtilV3; 
	
	//파일 임시저장소 저장
	@Override
	public Map<String, String> s3fileTempUpload(MultipartFile file) {
		return s3FileUploadUtilV3.s3TempUpload(file);
	}
	
	//임시저장소 -> src
	@Override
	public String s3fileTemptoSrc(String newName) {
		return s3FileUploadUtilV3.s3TempToSrc(newName);
	}

	//다중파일 업로드
	@Override
	public List<String> s3fileTemptoSrc(String[] newNames) {
		List<String> list = new ArrayList<>();
		
		for(int i=0; i<newNames.length; i++) {
			list.add(s3FileUploadUtilV3.s3TempToSrc(newNames[i]));
		}
		
		return list;
	}

	@Override
	public ResponseEntity<Resource> fileDownload(String newName, String orgName) {
		System.out.println("서비스 프로세스 실행");
		return s3FileUploadUtilV3.fileDownload(newName, orgName);
	}


}

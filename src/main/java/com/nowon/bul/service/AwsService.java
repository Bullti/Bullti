package com.nowon.bul.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

	Map<String, String> s3fileTempUpload(MultipartFile file);

	String s3fileTemptoSrc(String newName);
	
	List<String> s3fileTemptoSrc(String[] newNames);

	ResponseEntity<Resource> fileDownload(String fileUrl);

}

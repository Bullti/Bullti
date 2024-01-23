package com.nowon.bul.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

	Map<String, String> s3fileUploadPrecess(MultipartFile img);

}

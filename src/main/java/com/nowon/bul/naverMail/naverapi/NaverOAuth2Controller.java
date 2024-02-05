package com.nowon.bul.naverMail.naverapi;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.bul.naverMail.naverapi.response.MailBoxResponse;
import com.nowon.bul.naverMail.naverapi.response.MailFoldersResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NaverOAuth2Controller {
	
	private final OpenApiUtil openApiUtil;
	
	@Value("${naver.client.id}")
	private String clientId;
	@Value("${naver.client.secret}")
	private String clientSecret;
	@Value("${naver.client.domain}")
	private String domain;
	
	@GetMapping("/oauth2/v2.0/code")
	public String authrizeCode(@RequestParam("code") String code,@RequestParam("state") String state, Model model) throws IOException, Exception {
		if(!state.equals("test"))new Error("정상적인 요청경로가 아닙니다.");
		///token 생성
		StringBuilder sb=new StringBuilder("https://auth.worksmobile.com/oauth2/v2.0/token");
		sb.append("?code=");sb.append(code);
		sb.append("&grant_type=authorization_code");
		sb.append("&client_id=");sb.append(clientId);
		sb.append("&client_secret=");sb.append(clientSecret);
		sb.append("&domain=");sb.append(domain);
		String apiUrl=sb.toString();
		
		String method="POST";
		
		Map<String, String> requestHeaders=new HashMap<String, String>();
		requestHeaders.put("Content-Type", "application/x-www-form-urlencoded");
		
		String responseStr= openApiUtil.request(apiUrl, requestHeaders, method, null);
		//System.out.println("----responseStr:"+responseStr);
		
		NaverTokenDTO responseDTO=openApiUtil.objectMapper(responseStr, new TypeReference<NaverTokenDTO>() {});
		//ObjectMapper om=new ObjectMapper();
		//NaverTokenDTO responseDTO=om.readValue(responseStr, NaverTokenDTO.class);
		//System.out.println(responseDTO);
		
		
		///////
		//메일함 조회
		String userId="admin@yeseul-test.by-works.com";
		//String encodedUserId=;
		sb=new StringBuilder("https://www.worksapis.com/v1.0/users/");
				sb.append(URLEncoder.encode(userId,"UTF-8"));sb.append("/mail/mailfolders");
		apiUrl=sb.toString();
		requestHeaders=new HashMap<String, String>();
		requestHeaders.put("Authorization", responseDTO.getToken_type()+" "+responseDTO.getAccess_token());
		responseStr=openApiUtil.request(apiUrl, requestHeaders, "GET", null);
		//System.out.println("----responseStr:"+responseStr);
		
		MailFoldersResponse mailFoldersResponseDTO=openApiUtil.objectMapper(responseStr, new TypeReference<MailFoldersResponse>() {});
		//om=new ObjectMapper();
		//MailFoldersResponse mailFoldersResponseDTO=om.readValue(responseStr, MailFoldersResponse.class);
		//System.out.println(mailFoldersResponseDTO);
		//////////////////////////////////////////////////
		//////////////////////////////////////////////////
		//받은메일함
		// https://www.worksapis.com/v1.0/users/{userId}/mail/mailfolders/{folderId}/children
		
		sb=new StringBuilder("https://www.worksapis.com/v1.0/users/");
		sb.append(URLEncoder.encode(userId,"UTF-8"));sb.append("/mail/mailfolders/0/children");
		apiUrl=sb.toString();
		requestHeaders=new HashMap<String, String>();
		requestHeaders.put("Authorization", responseDTO.getToken_type()+" "+responseDTO.getAccess_token());
		responseStr=openApiUtil.request(apiUrl, requestHeaders, "GET", null);
		//System.out.println("----responseStr:"+responseStr);
		//om=new ObjectMapper();
		//MailBoxResponse mailBoxResponseDTO=om.readValue(responseStr, MailBoxResponse.class);
		MailBoxResponse mailBoxResponseDTO=openApiUtil.objectMapper(responseStr, new TypeReference<MailBoxResponse>() {});
		//System.out.println(mailBoxResponseDTO.getMails());
		model.addAttribute("boxList", mailFoldersResponseDTO.getMailFolders());
		model.addAttribute("list", mailBoxResponseDTO.getMails());
		return "/mail/mailList";
	}

}

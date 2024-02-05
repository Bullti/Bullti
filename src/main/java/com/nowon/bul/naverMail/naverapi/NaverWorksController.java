package com.nowon.bul.naverMail.naverapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.bul.naverMail.naverapi.response.Mail;
import com.nowon.bul.naverMail.naverapi.response.MailBoxResponse;
import com.nowon.bul.naverMail.naverapi.response.MailFolder;
import com.nowon.bul.naverMail.naverapi.response.MailFoldersResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NaverWorksController {
	
	private final OpenApiUtil openApiUtil;
	private final NaverWorksAPI naverWorksAPI;
	
	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.client.secret}")
	private String clientSecret;
	
	@Value("${naver.client.domain}")
	private String clientDomain;
	
	@Value("${naver.client.redirect-uri}")
	private String redirectUri;
	
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	static class AuthorizeDTO{
		private String clientId;
		private String redirectUri;
		private String scope;        //"mail,mail.read"
		private String state;        //"test"
		private String responseType; //"code"
		private String domain;
	}
	
	@GetMapping("/naver/works/mail")
	public String mailList(Model model){
		model.addAttribute("mail", AuthorizeDTO.builder()
				.clientId(clientId)
				.redirectUri(redirectUri)
				.scope("mail,mail.read")
				.state("test")
				.responseType("code")
				.domain(clientDomain)
				.build());
		return "naverapi/mail";
	}
	
	//redirect-uri
	//@GetMapping("/oauth2/v2.0/code")
	public String naverWorksAuthorize(@RequestParam("code") String code,@RequestParam("state") String state,
			HttpSession ssion) throws Exception, IOException {
		
		if(!state.equals("test")) new Error("정상적인 요청경로가 아닙니다.");
		
		NaverTokenDTO dto=generateToken(code);
		
		String userId="admin@coding.by-works.com";
		String encodedUserId=URLEncoder.encode(userId,"UTF-8");
		StringBuilder urlBuilder = new StringBuilder("https://www.worksapis.com/v1.0/users/"+encodedUserId+"/mail/mailfolders");
		urlBuilder.append("?code="+code);
		urlBuilder.append("&grant_type=authorization_code");
		urlBuilder.append("&client_id="+clientId);
		urlBuilder.append("&client_secret="+clientSecret);
		urlBuilder.append("&domain="+clientDomain);
		String apiUrl = urlBuilder.toString();
		
		
		Map<String, String> requestHeaders=new HashMap<>();
		requestHeaders.put("Authorization", dto.getToken_type()+" "+dto.getAccess_token());
		String responseJSONData=openApiUtil.request(apiUrl, requestHeaders, "GET", null);
		
		MailFoldersResponse result = objectMapper(responseJSONData, new TypeReference<MailFoldersResponse>() {});
		ssion.setAttribute("mailBoxList", result.getMailFolders());
		///////////////////////////////////////////////////////////////////////////////////////
		////받은메일함
		///////////////////////////////////////////////////////////////////////////////////////
		// https://www.worksapis.com/v1.0/users/{userId}/mail/mailfolders/{folderId}/children
		int folderId = 0;
		urlBuilder = new StringBuilder("https://www.worksapis.com/v1.0/users/" + encodedUserId + "/mail/mailfolders/"
				+ folderId + "/children");
		apiUrl = urlBuilder.toString();
		responseJSONData = openApiUtil.request(apiUrl, requestHeaders, "GET", null);
		MailBoxResponse mailBox = objectMapper(responseJSONData, new TypeReference<MailBoxResponse>() {});
		System.out.println("***:" + responseJSONData);
		
		ssion.setAttribute("box"+folderId, mailBox.getMails());
		return "redirect:/naverapi/mail-list";
		// return new ModelAndView("naverapi/mail-list").addObject("list",
		// result.getMailFolders());
	}
	
	@GetMapping("/naverapi/mail-list")
	public String mailBoxList(HttpSession ssion, Model model) {
		@SuppressWarnings("unchecked")
		List<MailFolder> boxList=(List<MailFolder>)ssion.getAttribute("mailBoxList");
		model.addAttribute("boxList", boxList);
		@SuppressWarnings("unchecked")
		List<Mail> list=(List<Mail>)ssion.getAttribute("box0");
		model.addAttribute("list", list);
		
		//ssion.removeAttribute("mailBoxList");
		return "naverapi/mail-list";
	}
	
	
	
	public <T> T objectMapper(String responseJSONData, TypeReference<T> typeReference) throws Exception {
		return  new ObjectMapper().readValue(responseJSONData, typeReference);
	}
	

	private NaverTokenDTO generateToken(String code) throws Exception, IOException {
		StringBuilder urlBuilder = new StringBuilder("https://auth.worksmobile.com/oauth2/v2.0/token");
		urlBuilder.append("?code="+code);
		urlBuilder.append("&grant_type=authorization_code");
		urlBuilder.append("&client_id="+clientId);
		urlBuilder.append("&client_secret="+clientSecret);
		urlBuilder.append("&domain=300124967");
		String apiUrl = urlBuilder.toString();
		
		Map<String, String> requestHeaders=new HashMap<>();
		requestHeaders.put("Content-Type", "application/x-www-form-urlencoded");
		
		String responseJSONData=openApiUtil.request(apiUrl, requestHeaders, "POST", null);
				
		return objectMapper(responseJSONData, new TypeReference<NaverTokenDTO>() {});
		
	}
	
}

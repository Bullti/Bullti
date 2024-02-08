package com.nowon.bul.naver;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.bul.naver.dto.NaverDeptSaveDTO;
import com.nowon.bul.utils.OpenApiUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NaverApiService {

	// @Value("${naver.ncp.access-key}")
	private String accessyKey = "acKQezNGQUukxZMY3DRE";

	// @Value("${naver.ncp.secret-key}")
	private String secretKey = "dLaS8OQyGulRWsRVLyQq45djC9QNM0BkjIAW8Kss";

	// @Value("${naver.ncp.company-id}")
	private String companyId = "235919e8-1b4b-41cb-a334-5ecff0631e37";

	private final OpenApiUtil api;

	/**
	 * 네이버API를 요청하기 위한 SignatureKey 생성
	 * 
	 * @param _method    : "POST" or "GET"
	 * @param _url       : 요청 url
	 * @param _timestamp : String.valueOf(System.currentTimeMillis());
	 * @return SHA256암호화를 사용한 SignatureKey
	 */
	private String makeSignature(String _method, String _url, String _timestamp) {
		String space = " "; // one space
		String newLine = "\n"; // new line
		String method = _method; // method
		String url = _url; // url (include query string)
		String timestamp = _timestamp; // current timestamp (epoch)
		String accessKey = accessyKey; // access key id (from portal or Sub Account)

		String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp)
				.append(newLine).append(accessKey).toString();

		SecretKeySpec signingKey;
		String encodeBase64String = null;
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeBase64String;
	}

	/**
	 * HttpHeader 헤더 생성
	 * 
	 * @param method : "POST" or "GET"
	 * @param url    : 요청url
	 * @return HttpHeader
	 */
	private Map<String, String> requestHttpHeader(String method, String url) {
		String timestamp = String.valueOf(System.currentTimeMillis());

		// sinature-key 생성 메서드
		String sinature = makeSignature(method, url, timestamp);

		Map<String, String> header = new HashMap<>();
		header.put("x-ncp-apigw-timestamp", timestamp);
		header.put("x-ncp-iam-access-key", accessyKey);
		header.put("x-ncp-apigw-signature-v2", sinature);
		return header;
	}

	/**
	 * 부서 등록
	 * 
	 * @param model
	 * @param dto   : HttpBody 내용
	 */
	public void saveDept(NaverDeptSaveDTO dto) {
		String url = "/organization/apigw/v2/company/" + companyId + "/department/" + dto.getDeptExternalKey();
		String method = "POST";
		String host = "https://workplace.apigw.ntruss.com";

		// 요청할때 필요한 헤더정보
		Map<String, String> httpHeader = requestHttpHeader(method, url);

		ObjectMapper mapper = new ObjectMapper();

		String httpBody = null;
		try {
			httpBody = mapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		httpHeader.put("Content-Type", "application/json");

		String jsonFormatStringresult = api.request(host + url, httpHeader, method, httpBody);
	}

	public void DeleteDept(int deptId) {
		String url = "/organization/apigw/v2/company/" + companyId + "/department/" + deptId;
		String method = "DELETE";
		String host = "https://workplace.apigw.ntruss.com";

		// 요청할때 필요한 헤더정보
		Map<String, String> httpHeader = requestHttpHeader(method, url);
		
		ObjectMapper mapper = new ObjectMapper();

		String httpBody = null;
		httpHeader.put("Content-Type", "application/json");

		String jsonFormatStringresult = api.request(host + url, httpHeader, method, httpBody);
	}
}

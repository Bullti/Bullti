package com.nowon.bul.naverMail.naverapi;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;

@Getter
@Component
public class NaverWorksAPI {
	
	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.client.secret}")
	private String clientSecret;
	
	@Value("${naver.service.account}")
	private String serviceAccount;
	
	@Value("${naver.private.key}")
	String privateKey;
	
	private static String base64URLEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
	
	public String generateTokenJwts(int min) {
        try {
           
            // JWT를 생성합니다.
            //Key key=Keys.hmacShaKeyFor(privateKey.getBytes("UTF-8"));
            Key key=getPrivateKey();
            String jwt = Jwts.builder()
            		.header()
            			.add("alg", "RS256").add("typ", "JWT")
            			.and()
            		.subject(serviceAccount) // 서브젝트 설정
            		.issuer(clientId)        // 이슈어 설정
            		.issuedAt(Date.from(ZonedDateTime.now().toInstant())) // 발급 시간 설정
                    .expiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant())) // 만료 시간 설정
                    .signWith(key)  
                    .compact();

            return jwt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	//RSA키를 이용하여 읽어서 PrivateKey타입으로
	public PrivateKey getPrivateKey() {
		
		String privateKeyPEM = privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
		        .replace("-----END PRIVATE KEY-----", "")
		        .replaceAll("\\s", "");
		// Base64 디코딩
		//System.out.println(">>>>:"+privateKeyPEM);
        byte[] keyBytes=null;
		try {
			keyBytes = Base64.getDecoder().decode(privateKeyPEM.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        // PKCS8EncodedKeySpec를 사용하여 PrivateKey 객체로 변환
        
        PrivateKey privateKey=null;
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        privateKey = keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}
	
	
	public String generateToken(int min) {
		
		String privateKeyPEM = privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
		        .replace("-----END PRIVATE KEY-----", "")
		        .replaceAll("\\s", "");
		
		// JSON 형식의 문자열을 만들기 위해 Jackson ObjectMapper를 사용합니다.
        ObjectMapper objectMapper = new ObjectMapper();
               
        String concatenated=null;
        try {
        	
			String header = objectMapper.writeValueAsString(Header.builder().alg("RS256").typ("JWT").build());
			String claims = objectMapper.writeValueAsString(Claims.builder()
					.iss(clientId)
					.sub(serviceAccount)
					.iat(Date.from(ZonedDateTime.now().toInstant()).toString())
					.exp(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()).toString())
					.build());
			String encodedHeader = base64URLEncode( header.getBytes("UTF-8") );
			String encodedClaims = base64URLEncode( claims.getBytes("UTF-8") );
			String encodedsignature  = base64URLEncode( privateKeyPEM.getBytes("UTF-8") );
			
			concatenated = encodedHeader + '.' + encodedClaims+'.'+ encodedsignature;
		} catch (JsonProcessingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		return concatenated;
	}
	
	
	
	// Header 클래스를 정의합니다.
	@Builder
	@Getter
    static class Header {
        private String alg;
        private String typ;
    }
	@Builder
	@Getter
	static class Claims{
		private String iss; //Developer Console에서 발급받은 앱의 client ID
		private String sub; //서비스 계정명(메일 주소 형식)
		private String iat; //JWT 생성 시간.Unix time으로 나타내며, 단위는 초(sec)이다.
		private String exp; //JWT 만료 시간.Unix time으로 나타내며, 단위는 초(sec)이다.
	}
	
	static class ClientInfo{
		
	}

}

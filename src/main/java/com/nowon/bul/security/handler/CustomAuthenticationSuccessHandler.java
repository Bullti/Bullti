package com.nowon.bul.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	//requestCache와 RedirectStragey를 사용하여 사용자가 인증 요청 성공시 
	//이전에 접근하려 했던 자원(리소스)의 경로로 바로 보내기 위해서 설정해줍니다.
	//이전의 접근하려 했던 자원(리소스)가 없는 경우 null을 반환하도록 하여 
	//setDefaultTargetUrl에 설정해준 url로 보내줍니다.
	private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    
    
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//기본 url 설정, savedRequest가 null일 경우 설정한 페이지로 보내기 위함이다.
        setDefaultTargetUrl("/");
        
        //사용자가 인증을 시도하기 이전에 접근을 시도했던 자원이 없을경우 savedRequest는 null로 반환된다.
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        
        if(savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
        
        System.out.println(">>CustomAuthenticationSuccessHandler실행");
        
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
	
}

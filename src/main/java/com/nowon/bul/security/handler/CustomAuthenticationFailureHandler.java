package com.nowon.bul.security.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String code;

		if (exception instanceof BadCredentialsException) {
			code="code1";
			//errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			code="code2";
			//errorMessage = "내부 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요. ";
		} else if (exception instanceof UsernameNotFoundException) {
			code="code3";
			//errorMessage = "존재하지 않는 계정입니다. 회원가입 후 로그인해주세요.";
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			code="code4";
			//errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
		} else {
			code="code5";
			//errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
		}
		
		code = URLEncoder.encode(code, "UTF-8"); /* 한글 인코딩 깨진 문제 방지 */
		
		setDefaultFailureUrl("/login?error&"+code);
		
		
		//부모클래스의 onAuthenticationFailure로 처리를 위임하자.
        super.onAuthenticationFailure(request, response, exception);
	}

}

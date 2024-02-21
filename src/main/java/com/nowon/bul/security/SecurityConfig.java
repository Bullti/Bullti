package com.nowon.bul.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nowon.bul.security.handler.CustomAuthenticationFailureHandler;
import com.nowon.bul.security.handler.CustomAuthenticationSuccessHandler;
import com.nowon.bul.security.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	

	//private final SessionRegistry sessionRegistry;
	
	//패스워드저장시 암호화 메서드
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }
	
	
	/*
	 * 로그인 시 입력한 username이 DB에 있는 username과 같은지 인증하는 메서드
	 * DB에 같은 username이 있다면 DB에서 해당하는 객체를 생성(username, password, roll)하고 반환
	 */
	@Bean 
	UserDetailsService userDetailsService() { return new CustomUserDetailsService(); }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// token을 사용하는 방식이기 때문에 csrf를 disable합니다.
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
						//.requestMatchers("/css/**", "/img/**", "/js/**", "/sql/**", "/summerNote/**", "/login", "/checkUser")
						.requestMatchers("/**")
						.permitAll()
						//.requestMatchers("/oms/**").hasRole("OMS")
						.anyRequest().authenticated())
//				.sessionManagement(sessionManagement -> sessionManagement
//						.maximumSessions(1) // 동시에 허용되는 세션 수
//						.maxSessionsPreventsLogin(true) // 세션 수 초과시 새로운 로그인 시도를 허용할 지 여부를 설정 true=로그인시도 차단
//						//.sessionRegistry(sessionRegistry) // session을 저장해 두는 sessionRegistr
//						.expiredUrl("/login") // 세션이 만료된 경우 이동 할 페이지
//				)
				.formLogin(
						formLogin -> formLogin.loginPage("/login").loginProcessingUrl("/login").usernameParameter("id") // default=username--form
								.passwordParameter("pass") // default=password--form
								.permitAll().defaultSuccessUrl("/") // 로그인 성공시 url (우선순위 마지막)
								.failureUrl("/login") // 로그인 실패시 url (우선순위 마지막)
								.loginProcessingUrl("/login") // form의 action 경로url, Default는"/login"
								.successHandler(customAuthenticationSuccessHandler)
								.failureHandler(customAuthenticationFailureHandler))
				.logout(logout -> logout
						.logoutSuccessUrl("/login")
						.logoutUrl("/logout")
						);

		return http.build();
	}

}

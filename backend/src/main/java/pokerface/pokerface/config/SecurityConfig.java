package com.gavoyage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CorsFilter;

import com.gavoyage.config.jwt.filter.JwtAuthorizationFilter;
import com.gavoyage.config.jwt.filter.JwtExceptionFilter;
import com.gavoyage.config.jwt.service.JwtService;
import com.gavoyage.config.login.filter.LoginFilter;
import com.gavoyage.config.oauth.handler.OAuth2LoginFailureHandler;
import com.gavoyage.config.oauth.handler.OAuth2LoginSuccessHandler;
import com.gavoyage.config.oauth.service.CustomOAuth2UserService;
//import com.gavoyage.config.login.handler.LoginFailureHandler;
//import com.gavoyage.config.login.handler.LoginSuccessHandler;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration // 스프링 설정 정보 
@EnableWebSecurity // 스프링 시큐리티 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CorsFilter corsFilter;
	private final UserServiceImpl userService;
	private final JwtService jwtService;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
//		http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);  // 두 번째 인자로 들어온 필터 이전에 수행할 사용자 정의 필터 지정
		http
			.csrf().disable() // rest api는 서버에 인증 정보를 저장하지 않기 때문에 csrf를 사용하지 않아도 된다.
			.addFilter(corsFilter)
			.formLogin().disable() // 스프링 세큐리티의 기본 form login 방식 비활성화
		
//		 HttpBasic: http 요청을 보낼 때 마다 Header 영역에  Authorization이라는 key 값으로 매번 id와 pw를 함께 보내는 방식
//		 HttpBearer: http 요청 시 Authorization이라는 key 값으로 idl, pw가 아닌 Token(ex. JWT)을 보내주는 방식  
			.httpBasic().disable(); // 기본 인증 방식 비활성화
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않도록 설정
		http.authorizeHttpRequests()
		.antMatchers("/", "/users/join", "/swagger-ui/**", "/users/emailCheck/**", "/users/nicknameCheck/**", "/regions/**", "/css/**", "/img/**", "/js/**", "/favicon.ico", 
				"/v3/api-docs", "/swagger-resources/**", "/swagger-ui.html","/swagger-ui/**").permitAll()
		.antMatchers(HttpMethod.GET, "/reviews/**").permitAll()
		.antMatchers("/reviews/users", "/reviews/likes").authenticated()
//		.antMatchers(HttpMethod.POST, "/reviews/**").hasRole("USER")
		.anyRequest().authenticated() // 나머지 요청들은 로그인 없어도 허용
		
		/**
		 * 소셜 로그인 관리	
		 */
		.and()
		.oauth2Login()
        .successHandler(oAuth2LoginSuccessHandler) // 소셜 로그인 성공 시 호출
        .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 호출
        .userInfoEndpoint().userService(customOAuth2UserService); // OAuth2 로그인 로직 담당하는 서비스 설정
		
		http.addFilterAfter(new LoginFilter(authenticationManager(), jwtService, userService), LogoutFilter.class); // 로그인 시 정상 회원이라면 jwt 토큰을 생성해주는 필터
		http.addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), jwtService, userService), LoginFilter.class); // 회원용 api 호출 시 jwt의 유효성을 검사해주는 필터
		http.addFilterBefore(new JwtExceptionFilter(), JwtAuthorizationFilter.class); // jwt 토큰 유효성 검사해주는 필터
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() { // 비밀번호 암호화 
		return new BCryptPasswordEncoder();
	}

	
}

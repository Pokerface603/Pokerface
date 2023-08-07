package com.gavoyage.config.login.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavoyage.config.jwt.service.JwtService;
import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.config.login.UserResponse;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

// 스프링 시큐리티는 기본적으로 로그인 요청이 오면 주소가 http://localhost:8080/login으로 오게되고 UsernamePasswordAuthenticationFilter가 동작하여
// login 요청 시 username, userpassword를 전송하면(by POST) 동작한다.
// 하지만 SecurityConfig에서 .formLogin().disable()을 적용해놨기 때문에 별도의 filter를 필요로 하여 이와 같이 사용자 정의 필터를 생성하고
// UsernamePasswordAuthenticationFilter를 상속받으면 "/login" 주소로 오는 POST 요청을 빼앗아 실행된다.

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserServiceImpl userService;
	
	public LoginFilter(AuthenticationManager authenticationManager, JwtService jwtService,
			UserServiceImpl userService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userService = userService;
	}

	// "/login"으로 요청을 하면  username, userpassword를 받아 로그인을 시도하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		log.debug("로그인 시도 ");
		System.out.println("JwtAuthenticationFilter : 로그인 시도");
		System.out.println("request.getRequestURI() : " + request.getRequestURL());
		
		try {
			// 1. username, userpassword를 받아옴
			ObjectMapper om = new ObjectMapper();
			UserLoginReq user = om.readValue(request.getInputStream(), UserLoginReq.class);
			
			log.debug("json to userLoginREq 완료 ");
			
			// UsernamePasswordAuthenticationToken 생성(로그인 정보를 지닌다)
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			// authenticationManager가 생성한 토큰으로 인증(authenicate) 시도
			// PrincipalDetailsService의 loadUserByUsername을 내부적으로 호출하고 올바른 아이디, 패스워드라면 authentication을 return 한다.
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			// 로그인이 되었는지 principalDetails 출력하여 확인(유저가 출력된다면 로그인 성공), 꼭 필요한 코드는 아님!
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			log.debug("principalDetails.getUser() : " + principalDetails.getUser());
			
			// authentication(사용자 정보)가 session 영역에 저장됨
			// 굳이 JWT를 사용하면서 세션을 만들 이유는 없지만 유저의 권환 관리를 위해 session에 넣어줌
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
//	 attemptAuthentication에서 인증이 정상적으로 되었다면 실행되는 메소드
//	 이 메소드에서 JWT를 생성하고 response에 JWT 토큰을 담아 응답
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		log.debug("로그인 성공");
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		Users user = principalDetails.getUser();
		
		String accessToken = jwtService.createAccessToken(user.getEmail(), user.getNickname());
		String refreshToken = jwtService.createRefreshToken();
		
		log.debug("refreshToken : " + refreshToken);
		
		jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
		
		/**
		 * 로그인한 사용자 refresh token 값 갱신
		 */
		log.debug("=====login 시 발급한 refresh token=====");
		userService.updateRefreshToken(user.getEmail(), refreshToken);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userResponse = objectMapper.writeValueAsString(new UserResponse(user.getNickname(), user.getEmail()));
		response.getWriter().print(userResponse);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		log.debug("로그인 실패");
		
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("로그인 실패");
        
        log.info("로그인에 실패했습니다. 메시지 : {}", failed.getMessage());
	}

}

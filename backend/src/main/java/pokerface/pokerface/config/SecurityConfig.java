package pokerface.pokerface.config;

import com.mysql.cj.log.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;
import pokerface.pokerface.config.jwt.filter.JwtAuthorizationFilter;
import pokerface.pokerface.config.jwt.filter.JwtExceptionFilter;
import pokerface.pokerface.config.jwt.service.JwtService;
import pokerface.pokerface.config.login.filter.LoginFilter;
import pokerface.pokerface.domain.member.service.MemberService;

@Configuration // 스프링 설정 정보 
@EnableWebSecurity // 스프링 시큐리티 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CorsFilter corsFilter;
	private final MemberService memberService;
	private final JwtService jwtService;
//	private final CustomOAuth2UserService customOAuth2UserService;
//	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
//	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // rest api는 서버에 인증 정보를 저장하지 않기 때문에 csrf를 사용하지 않아도 된다.
			.addFilter(corsFilter) // 커스텀 cors 필터 추가
			.formLogin().disable() // 스프링 세큐리티의 기본 form login 방식 비활성화

//		 HttpBasic: http 요청을 보낼 때 마다 Header 영역에  Authorization이라는 key 값으로 매번 id와 pw를 함께 보내는 방식
//		 HttpBearer: http 요청 시 Authorization이라는 key 값으로 id, pw가 아닌 Token(ex. JWT)을 보내주는 방식
			.httpBasic().disable() // 기본 인증 방식 비활성화
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않도록 설정

		http.authorizeHttpRequests()
				// 1. 테스트를 용이하기 위한 버전
//				.antMatchers("/members/login").authenticated()
//						.anyRequest().permitAll();
				// 2. 추후 실제로 사용해야하는 버전
				.antMatchers("/",  "/members","/members/check/email/**", "/members/check/nickname/**"
						,"/utils/profile", "/actuator/health", "/members/email/confirm",
						"/api/swagger-ui/**","/swagger-resources/**", "/swagger-ui.html","/swagger-ui/**").permitAll()
				.anyRequest().authenticated(); // 나머지 요청들은 인증을 필요로 한다.
		
		/**
		 * 소셜 로그인 관리	
		 */
//		.and()
//		.oauth2Login()
//        .successHandler(oAuth2LoginSuccessHandler) // 소셜 로그인 성공 시 호출
//        .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 호출
//        .userInfoEndpoint().userService(customOAuth2UserService); // OAuth2 로그인 로직 담당하는 서비스 설정
//

		http.addFilterAfter(new LoginFilter(authenticationManager(), jwtService, memberService), LogoutFilter.class); // 로그인 시 정상 회원이라면 jwt 토큰을 생성해주는 필터
		http.addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), jwtService, memberService), LoginFilter.class); // 회원용 api 호출 시 jwt의 유효성을 검사해주는 필터
		http.addFilterBefore(new JwtExceptionFilter(), JwtAuthorizationFilter.class); // jwt 토큰 유효성 검사해주는 필터
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() { // 비밀번호 암호화 
		return new BCryptPasswordEncoder();
	}
}

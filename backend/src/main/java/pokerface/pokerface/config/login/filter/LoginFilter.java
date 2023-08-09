package pokerface.pokerface.config.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.config.jwt.service.JwtService;
import pokerface.pokerface.config.login.PrincipalDetails;
import pokerface.pokerface.domain.member.dto.request.MemberLoginReq;
import pokerface.pokerface.domain.member.dto.response.MemberLoginRes;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.service.MemberService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 스프링 시큐리티는 기본적으로 로그인 요청이 오면 주소가 http://localhost:8080/login으로 오게되고 UsernamePasswordAuthenticationFilter가
// login 요청 시 username, userpassword를 전송하면(by POST) 동작한다.
// 하지만 SecurityConfig에서 .formLogin().disable()을 적용해놨기 때문에 별도의 filter를 필요로 하여 이와 같이 사용자 정의 필터를 생성하고
// UsernamePasswordAuthenticationFilter를 상속받으면 "/login" 주소로 오는 POST 요청을 빼앗아 실행된다.

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter{

	private final String filterProcessesUrl = "/members/login";
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final MemberService memberService;

	public LoginFilter(AuthenticationManager authenticationManager, JwtService jwtService,
					   MemberService memberService) {
		log.debug("Login Filter Called");
		log.debug("filterProcessUrl : {}", filterProcessesUrl);

		setFilterProcessesUrl(filterProcessesUrl);

		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.memberService = memberService;
	}

	// "/login"으로 요청을 하면  username, userpassword를 받아 로그인을 시도하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		log.debug("JwtAuthenticationFilter : 로그인 시도");
		log.debug("request.getRequestURI() : " + request.getRequestURL());

		// 1. 로그인 시 json으로 받아온 username, userpassword를 MemberLoginReq 형태로 변환(json to java object)
		ObjectMapper om = new ObjectMapper();
		MemberLoginReq memberLoginReq = null;

		try {
			memberLoginReq = om.readValue(request.getInputStream(), MemberLoginReq.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		log.debug("json to MemberLoginReq 완료 ");

		// UsernamePasswordAuthenticationToken 생성(로그인 정보를 지닌다)
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(memberLoginReq.getUsername(), memberLoginReq.getPassword());

		// authenticationManager가 위에서 생성한 토큰으로 인증(authenicate) 시도
		// PrincipalDetailsService의 loadUserByUsername을 내부적으로 호출하고 올바른 아이디, 패스워드라면 authentication을 return 한다.
		Authentication authentication = authenticationManager.authenticate(authenticationToken);

		// 로그인이 되었는지 principalDetails 출력하여 확인(유저가 출력된다면 로그인 성공), 꼭 필요한 코드는 아님!
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		log.debug("principalDetails.getUser() : " + principalDetails.getMember());

		// authentication(사용자 정보)가 session 영역에 저장됨
		// 굳이 JWT를 사용하면서 세션을 만들 이유는 없지만 유저의 권환 관리를 위해 session에 넣어줌
		return authentication;
	}

	/**
	 * attemptAuthentication에서 인증이 정상적으로 되었다면 실행되는 메소드
	 * 이 메소드에서 JWT를 생성하고 response에 JWT 토큰을 담아 응답
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		log.debug("로그인 성공");

		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		Member member = principalDetails.getMember();

		if (!member.getEmailAuth()) {
			throw new RestException(ErrorCode.EMAIL_NOT_AUTHENTICATED);
		}

		String accessToken = jwtService.createAccessToken(member.getEmail(), member.getNickname());
		String refreshToken = jwtService.createRefreshToken();

		// 로그인 성공 시 access token과 refresh token을 응답에 넣어서 return
		jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
		
		// 로그인 시 매번 사용자의 refresh token 값 갱신
		jwtService.updateRefreshToken(member, refreshToken);

		// java object to json
		ObjectMapper objectMapper = new ObjectMapper();
		String memberResponse = objectMapper.writeValueAsString(new MemberLoginRes(member.getNickname(), member.getEmail()));
		response.getWriter().print(memberResponse);
	}

	/**
	 * 로그인 실패 시 호출 될 핸들러
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		log.debug("로그인 실패");
		
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write("로그인 실패");
        
        log.debug("로그인에 실패했습니다. 메시지 : {}", failed.getMessage());
	}

}

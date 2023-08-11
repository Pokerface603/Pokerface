package pokerface.pokerface.config.oauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pokerface.pokerface.config.jwt.service.JwtService;
import pokerface.pokerface.config.oauth.CustomOAuth2User;
import pokerface.pokerface.domain.member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private final JwtService jwtService;
	private final MemberService memberService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.debug("소셜 로그인 성공");
		
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		
		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail(), oAuth2User.getNickname());
		String refreshToken = jwtService.createRefreshToken();
		
		memberService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);

		String uri = UriComponentsBuilder.fromUriString("https://pokerface-server.ddns.net/lobby")
				.queryParam("accessToken", accessToken)
				.queryParam("refreshToken", refreshToken)
				.build().toUriString();
		response.sendRedirect(uri);
	}
}

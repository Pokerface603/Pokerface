package pokerface.pokerface.config.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.repository.MemberRepository;
import pokerface.pokerface.domain.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
	/**
	 * @Value 어노테이션을 사용해 application-jwt.yml에 담긴 jwt 관련 설정 정보들을 불러옴
	 */
	@Value("${jwt.secretKey}")
    private String secretKey;
	
	@Value("${jwt.tokenPrefix}")
	private String tokenPrefix;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationTime;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationTime;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

	/**
	 * AccessToken 생성("Bearer ~~~" )
	 */
   public String createAccessToken(String email, String nickname) {
	   return JWT.create()
			   .withSubject("AccessToken") // jwt의 subject를 지정해주는데 사실 아무거나 사용해도 상관 없다
			   .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpirationTime)) // access token의 유효기간 설정
			   .withClaim("email", email) // 클레임에 유저 이메일 추가
			   .withClaim("nickname", nickname) // 클레임에 유저 닉네임 추가
			   .sign(Algorithm.HMAC512(secretKey)); // 서명에 시크릿 키 지정
   }

	/**
	 * RefreshToken 생성
	 */
	public String createRefreshToken() {
	   return JWT.create()
			   .withSubject("RefreshToken") // jwt의 subject를 지정해주는데 사실 아무거나 사용해도 상관 없다
			   .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpirationTime)) // JWT 유효기간
			   .sign(Algorithm.HMAC512(secretKey)); // 서명에 시크릿 키 지정
   }
   
   public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
	   response.setContentType("application/json;charset=UTF-8"); // 응답의 content-type 지정
       response.setStatus(HttpServletResponse.SC_OK); // 응답 상태 코드 지정
       
       sendAccessToken(response, accessToken);
       sendRefreshToken(response, refreshToken);
   }

	/**
	 * access token을 응답 헤더에 추가
	 */
	public void sendAccessToken(HttpServletResponse response, String accessToken) {
	   response.setHeader(accessHeader, tokenPrefix + accessToken);
   }

	/**
	 * refresh token을 응답 헤더에 추가
	 */
   public void sendRefreshToken(HttpServletResponse response, String refreshToken) {
	   response.setHeader(refreshHeader, tokenPrefix + refreshToken);
   }

	/**
	 * Access token 추출
	 * 앞에 붙은 Bearer을 제거하고 토큰 값만 return
	 */
	public Optional<String> extractAccessToken(HttpServletRequest request) {
	   log.debug("accessHeader : " + accessHeader);

	   if(request.getHeader(accessHeader) == null) {
		   log.debug("====extractAccessToken is Null!!====");

	   }
	   log.debug("Bearer access token : " + request.getHeader(accessHeader));

	   return Optional.ofNullable(request.getHeader(accessHeader))
	   			.filter(token -> token.startsWith(tokenPrefix))
	   			.map(token -> token.replace(tokenPrefix, ""));
   }

	/**
	 * Refresh token 추출
	 * 앞에 붙은 Bearer을 제거하고 토큰 값만 return
	 */
   public Optional<String> extractRefreshToken(HttpServletRequest request) {
	   log.debug("refreshHeader : " + refreshHeader);

	   if(request.getHeader(refreshHeader) == null) {
		   log.debug("====extractRefreshToken is Null!!====");
	   }

	   log.debug("Bearer refresh token : " + request.getHeader(refreshHeader));

	   return Optional.ofNullable(request.getHeader(refreshHeader))
			   			.filter(token -> token.startsWith(tokenPrefix))
			   			.map(token -> token.replace(tokenPrefix, ""));
   }

	/**
	 * JWT의 클레임에 담아두었던 email 추출
	 */
	public Optional<String> extractEmail(String accessToken){
	   try {
		   return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey)) // jwt verifier builder를 불러옴 
						.build()              // 불러온 jwt verifier builder로 jwt verifier 생성
						.verify(accessToken)  // access token 유효성 검증
						.getClaim("email")
						.asString());   
	   } catch(SignatureVerificationException e) {
    	   log.info("시그니처 검증 미통과");
           throw new JwtException("시그니처 검증 미 통과");
       } catch(TokenExpiredException e) {
    	   log.info("JWT 토큰 만료");
           throw new JwtException("JWT 토큰 만료");
       }  catch (IllegalArgumentException e) {
           log.info("JWT token compact of handler are invalid.");
           throw new JwtException("JWT token compact of handler are invalid.");
       }
   }

	/**
	 * Refresh token 갱신
	 */
	public void updateRefreshToken(Member member, String refreshToken) {
		member.setRefreshToken(refreshToken);
		memberRepository.saveAndFlush(member); // db에 반영
   }

	/**
	 * 토큰 유효성 검사
	 */
	public boolean isTokenValid(String token) {
       try {
    	   log.debug("isValid token : " +  token);
           JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
           return true;
       } catch(SignatureVerificationException e) {
    	   log.info("시그니처 검증 미통과");
           throw new JwtException("시그니처 검증 미 통과");
       } catch(TokenExpiredException e) {
    	   log.info("JWT 토큰 만료");
           throw new JwtException("JWT 토큰 만료");
       }  catch (IllegalArgumentException e) {
           log.info("JWT token compact of handler are invalid.");
           throw new JwtException("JWT token compact of handler are invalid.");
       }
   }
}

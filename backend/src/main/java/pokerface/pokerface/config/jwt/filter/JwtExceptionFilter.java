package pokerface.pokerface.config.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 토큰 검증 시 에러를 핸들링 할 필터
 */
@Component
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.debug("Jwt Exception Filter Called");
			filterChain.doFilter(request, response); // JWT 인증 필터로 이동
		} catch (JwtException e) { // JWT 인증 필터에서 JWT 관련 예외 발생 시 catch 후 응답
            log.debug("Jwt Exception Filter에서 예외 catch");
			setErrorResponse(request, response, e);
		}
	}

    /**
     * 에러 발생 시 응답 형태를 지정
     */
    public void setErrorResponse(HttpServletRequest req, HttpServletResponse res, Throwable ex) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final Map<String, Object> body = new HashMap<>(); // 응답 body

        log.debug(ex.getMessage());

        if(ex.getMessage().equals("이메일 인증이 필요합니다.")) {
            return;
        }

        if(ex.getMessage().equals("JWT 토큰 만료")) {
            body.put("status", 5000); // 토큰 만료 시
        }

        if(ex.getMessage().equals("시그니처 검증 미 통과") || ex.getMessage().equals("JWT token compact of handler are invalid."))
        {
            body.put("status", 6000); // 시그니처 검증 미 통과 시
        }

        body.put("error", "Unauthorized");
        body.put("message", ex.getMessage());
        body.put("path", req.getServletPath());
        
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(res.getOutputStream(), body);
        res.setStatus(HttpServletResponse.SC_OK);
    }
}

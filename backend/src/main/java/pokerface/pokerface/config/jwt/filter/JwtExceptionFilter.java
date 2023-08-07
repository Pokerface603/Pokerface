package com.gavoyage.config.jwt.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			System.out.println("Jwt Exception Filter Called");
			filterChain.doFilter(request, response); // JWT 인증 필터로 이동
		} catch (JwtException e) { // JWT 인증 필터에서 JWT 관련 예외 발생 시 catch 후 응답
			System.out.println("Jwt Exception Filter에서 예외 catch");
			setErrorResponse(request, response, e);
		}
	}

    public void setErrorResponse(HttpServletRequest req, HttpServletResponse res, Throwable ex) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        final Map<String, Object> body = new HashMap<>(); // 응답 body
        
        System.out.println(ex.getMessage());
        if(ex.getMessage().equals("JWT 토큰 만료")) {
        	System.out.println("JWT 토큰 만료");
        	body.put("status", 5000); // 토큰 만료 시
        }else {
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

package com.gavoyage.config.jwt;

public interface JwtProperties {
	String SECRETE = "SEOJIO"; // 시크릿 키
	String TOKEN_PREFIX = "Bearer "; // Http 메소드에 명시된 인증 방식
	String HEADER_STRING = "Authorization"; // 요청 헤더 key값
}

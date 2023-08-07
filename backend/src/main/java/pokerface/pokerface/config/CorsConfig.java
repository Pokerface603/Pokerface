package pokerface.pokerface.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		// 서버 응답 시 CORS 처리
		config.setAllowCredentials(true); // api에서 응답으로 보내는 json을 자바스크립트에서 처리할 수 있도록 설정 
		config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용
		config.addAllowedHeader("*"); // 모든 header에 응답 허용
		config.addAllowedMethod("*"); // 모든 http method(post, get, put, delete, patch) 요청 허용
		source.registerCorsConfiguration("/**", config);
		
        config.addExposedHeader("Authorization"); // 응답 헤더에 Access Token을 노출
        config.addExposedHeader("Authorization-refresh"); // 응답 헤더에 Refresh Token을 노출
		return new CorsFilter(source);
	}
}

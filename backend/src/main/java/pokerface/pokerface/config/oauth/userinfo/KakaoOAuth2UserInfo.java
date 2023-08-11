package pokerface.pokerface.config.oauth.userinfo;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo{
	
	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return String.valueOf(attributes.get("id")); // 카카오는 Long type을 return하기 때문에 String.valueOf()로 캐스팅
	}

	@Override
	public String getNickName() {
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		
		if(kakaoAccount == null) {
			return null;
		}
		
		Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
		log.debug("profile : {}", profile);
		
		if(profile == null) {
			return null;
		}
		
		log.debug("nickname : {}", (String) profile.get("nickname"));
		
		return (String) profile.get("nickname");
	}

	@Override
	public String getEmail() {
		log.debug("getEmail called : {}", attributes.toString());
		
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		
		if(kakaoAccount == null) {
			return null;
		}
		
		if( (boolean) kakaoAccount.get("has_email")) {
			return (String) kakaoAccount.get("email");
		}
		
		return null;
	}
}

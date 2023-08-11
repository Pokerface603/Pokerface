package pokerface.pokerface.config.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pokerface.pokerface.config.oauth.userinfo.KakaoOAuth2UserInfo;
import pokerface.pokerface.config.oauth.userinfo.NaverOAuth2UserInfo;
import pokerface.pokerface.config.oauth.userinfo.OAuth2UserInfo;

import java.util.Map;

@Getter
@Slf4j
public class OAuthAttributes {
	
	private String nameAttributeKey;
	private OAuth2UserInfo oAuth2UserInfo;
	
	@Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }
	
	public static OAuthAttributes of(String socialType, String userNameAttributeName, Map<String, Object> attributes) {
		if(socialType.equals("naver")) {
			return ofNaver(userNameAttributeName, attributes);
		}
		if(socialType.equals("kakao")) {
			return ofKakao(userNameAttributeName, attributes);
		}
		throw new IllegalStateException("해당하는 도메인이 존재하지 않습니다");
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.nameAttributeKey(userNameAttributeName)
				.oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
				.build();
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.nameAttributeKey(userNameAttributeName)
				.oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
				.build();
	}
}

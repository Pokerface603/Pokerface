package pokerface.pokerface.config.oauth.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo{

	public NaverOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() { 
		Map<String, Object> responses = (Map<String, Object>) attributes.get("response");
		
		if(responses == null) { // response에 해당하는 key가 존재하지 않을 경우
			return null;
		}
		
		return (String) responses.get("id");
	}

	@Override
	public String getNickName() {
		Map<String, Object> responses = (Map<String, Object>) attributes.get("response");
		
		if(responses == null) { // response에 해당하는 key가 존재하지 않을 경우
			return null;
		}
		
		return (String) responses.get("nickname");
	}
	
	@Override
	public String getEmail() {
		
		if( (boolean) attributes.get("email")) {
			return (String) attributes.get("email");
		}
		
		return null;
	}

}

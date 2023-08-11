package pokerface.pokerface.config.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import pokerface.pokerface.domain.member.entity.Role;

import java.util.Collection;
import java.util.Map;

/**
 * 소셜 로그인 시 authorization server로 부터 받지 못하지만 서비스 내에서 필요한 정보들을 추가로 받기 위해 사용
 * DefaultOAuth2User : 스프링 세큐리티의 UserDetails와 같은 역할
 */
@Getter @Setter
public class CustomOAuth2User extends DefaultOAuth2User{
	private String email;
	private Role userRole;
	private String nickname;
	
	public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
			String nameAttributeKey, String email, Role userRole, String nickname) {
		super(authorities, attributes, nameAttributeKey);
		this.email = email;
		this.userRole = userRole;
		this.nickname = nickname;
	}
}

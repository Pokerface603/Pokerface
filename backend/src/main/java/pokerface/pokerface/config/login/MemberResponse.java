package pokerface.pokerface.config.login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
	private String nickname;
	private String email;
	
	public MemberResponse(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
	}
}

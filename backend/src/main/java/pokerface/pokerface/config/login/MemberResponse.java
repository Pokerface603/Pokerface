package pokerface.pokerface.config.login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
	private String nickname;
	private String email;
	
	public UserResponse(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
	}
}

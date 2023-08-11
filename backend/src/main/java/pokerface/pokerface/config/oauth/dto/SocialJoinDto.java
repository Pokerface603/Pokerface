package pokerface.pokerface.config.oauth.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SocialJoinDto {
	// authorization server로 부터 받는 정보
	private String socialType;
	private String socialId;
	private String nickname;
	
	// 임의로 지정할 데이터
	private String email;
	private String userPassword;
}

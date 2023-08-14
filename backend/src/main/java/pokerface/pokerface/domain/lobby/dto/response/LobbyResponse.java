package pokerface.pokerface.domain.lobby.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.member.dto.response.MemberLoginRes;

@Builder
@Getter
public class LobbyResponse {
    private String nickname;

    private String email;

    private Boolean isFriend;

    public static LobbyResponse of(MemberLoginRes memberLoginRes, Boolean isFriend){
        return new LobbyResponse(memberLoginRes.getNickname(), memberLoginRes.getEmail(), isFriend);
    }
}

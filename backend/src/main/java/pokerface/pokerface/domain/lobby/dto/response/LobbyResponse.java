package pokerface.pokerface.domain.lobby.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.member.entity.Tier;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class LobbyResponse {
    private String nickname;

    private String email;

    private Boolean isFriend;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    public static LobbyResponse of(String nickname, String email, Boolean isFriend, Tier tier){
        return new LobbyResponse(nickname, email, isFriend, tier);
    }
}

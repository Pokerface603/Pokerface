package pokerface.pokerface.domain.lobby.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.member.entity.Tier;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class LobbyFriendsResponse {
    private final String friendNickname;

    private final RoomInfoRes roomInfoRes;

    @Enumerated(EnumType.STRING)
    private final Tier tier;

    public static LobbyFriendsResponse of(String friendNickname, RoomInfoRes roomInfoRes, Tier tier){
        return new LobbyFriendsResponse(friendNickname, roomInfoRes, tier);
    }
}

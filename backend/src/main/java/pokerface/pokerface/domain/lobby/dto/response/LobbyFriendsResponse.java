package pokerface.pokerface.domain.lobby.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;

@Builder
@Getter
public class LobbyFriendsResponse {
    private final String friendNickname;

    private final RoomInfoRes roomInfoRes;

    public static LobbyFriendsResponse of(String friendNickname, RoomInfoRes roomInfoRes){
        return new LobbyFriendsResponse(friendNickname, roomInfoRes);
    }
}

package pokerface.pokerface.domain.friend.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.friend.entity.Friend;

@Builder
@Getter
public class FriendResponse {
    private final Long friendId;

    private final Long fromId;

    private final Long toId;

    public static FriendResponse of(Friend friend){
        return new FriendResponse(friend.getId(), friend.getFrom().getId(), friend.getTo().getId());
    }
}

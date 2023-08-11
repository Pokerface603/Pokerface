package pokerface.pokerface.domain.friend.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.friend.entity.Friend;

@Builder
@Getter
public class FriendResponse {
    private final Long friendId;

    private final String fromEmail;

    private final String toEmail;

    public static FriendResponse of(Friend friend){
        return new FriendResponse(friend.getId(), friend.getFrom().getEmail(), friend.getTo().getEmail());
    }
}

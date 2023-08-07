package pokerface.pokerface.domain.friend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pokerface.pokerface.domain.friend.entity.Friend;
import pokerface.pokerface.domain.member.entity.Member;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    private Long fromId;

    private Long toId;

    public Friend toFriend(Member from, Member to){
        return new Friend(from, to);
    }
}

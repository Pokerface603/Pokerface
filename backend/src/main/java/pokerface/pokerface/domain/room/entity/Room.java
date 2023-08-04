package pokerface.pokerface.domain.room.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import pokerface.pokerface.domain.member.entity.Member;

import java.util.List;

@Getter
@Setter
@RedisHash(value = "room", timeToLive = 100000)
@NoArgsConstructor
@ToString
public class Room {
    @Id
    private String sessionId;

    @Indexed
    private String title;

    @Indexed
    private String gameMode;

    private Boolean isPrivate;

    private String roomPassword;

    private List<Member> members;

    @Builder
    public Room(String sessionId, String gameMode, String title, Boolean isPrivate, String roomPassword, List<Member> members) {
        this.sessionId = sessionId;
        this.gameMode = gameMode;
        this.title = title;
        this.isPrivate = isPrivate;
        this.roomPassword = roomPassword;
        this.members = members;
    }
}

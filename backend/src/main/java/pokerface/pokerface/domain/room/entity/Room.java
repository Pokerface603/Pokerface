package pokerface.pokerface.domain.room.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.member.entity.Member;

import java.time.LocalDateTime;
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
    private GameMode gameMode;

    private Boolean isPrivate;

    private String roomPassword;

    private LocalDateTime createdAt;

    private List<Member> members;

    @Builder
    public Room(String sessionId, GameMode gameMode, String title, Boolean isPrivate, String roomPassword, List<Member> members, LocalDateTime createdAt) {
        this.sessionId = sessionId;
        this.gameMode = gameMode;
        this.title = title;
        this.isPrivate = isPrivate;
        this.roomPassword = roomPassword;
        this.members = members;
        this.createdAt = createdAt;
    }
}

package pokerface.pokerface.domain.room.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.global.BaseTime;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RedisHash(value = "room", timeToLive = 100000)
@NoArgsConstructor
@ToString
public class Room {
    @Id
    private Long id;

    private String sessionId;

    private String title;

    private Boolean isPrivate;

    private String roomPassword;

    private List<Member> members;

    @Builder
    public Room(String sessionId, String title, Boolean isPrivate, String roomPassword, List<Member> members) {
        this.sessionId = sessionId;
        this.title = title;
        this.isPrivate = isPrivate;
        this.roomPassword = roomPassword;
        this.members = members;
    }
}

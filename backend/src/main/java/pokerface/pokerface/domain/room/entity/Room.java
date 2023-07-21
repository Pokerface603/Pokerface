package pokerface.pokerface.domain.room.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.global.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member host;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Member guest;
}

package pokerface.pokerface.domain.history.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.global.BaseTime;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class History extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    private String gameLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Member guest;

    public History(String gameMode, String gameLog, Member host, Member guest){
        this.gameMode = GameMode.valueOf(gameMode);
        this.gameLog = gameLog;
        this.host = host;
        this.guest = guest;
    }
}

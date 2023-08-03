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

    public History(String gameMode){
        this.gameMode = GameMode.valueOf(gameMode);
    }
}

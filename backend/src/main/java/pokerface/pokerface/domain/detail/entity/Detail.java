package pokerface.pokerface.domain.detail.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.global.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Detail extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameLog;

    private Integer preRating;

    private Integer postRating;

    @Enumerated(EnumType.STRING)
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    private History history;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

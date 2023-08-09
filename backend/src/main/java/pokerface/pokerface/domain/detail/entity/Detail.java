package pokerface.pokerface.domain.detail.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.global.BaseTime;

import javax.persistence.*;

import static pokerface.pokerface.global.Constants.BOUNTY_RATIO;
import static pokerface.pokerface.global.Constants.ROUND_UNIT;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Detail extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Detail(Integer preRating, Integer postRating, Result result, History history, Member member){
        this.preRating = preRating;
        this.postRating = postRating;
        this.result = result;
        this.history = history;
        this.member = member;
    }

    public Long convertRatingToBounty(Integer rating){
        return Math.round(Math.pow(BOUNTY_RATIO, rating) / ROUND_UNIT) * ROUND_UNIT;
    }
}

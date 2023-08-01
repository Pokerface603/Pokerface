package pokerface.pokerface.domain.detail.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRequest {
    private String gameLog;

    private Integer preRating;

    private Integer postRating;

    private String result;

    public Detail toDetail(History history, Member member){
        return new Detail(gameLog, preRating, postRating, result, history, member);
    }
}

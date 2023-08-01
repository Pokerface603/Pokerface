package pokerface.pokerface.domain.detail.dto.request;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;


@Getter
public class DetailRequest {
    @Builder.Default
    private String gameLog;

    @Builder.Default
    private Integer preRating;

    @Builder.Default
    private Integer postRating;

    @Builder.Default
    private String result;

    private DetailRequest(){}

    public Detail toDetail(History history, Member member){
        return new Detail(gameLog, preRating, postRating, result, history, member);
    }
}

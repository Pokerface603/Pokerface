package pokerface.pokerface.domain.detail.dto.request;

import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;

public class DetailRequest {
    String gameLog;

    Integer preRating;

    Integer postRating;

    String result;

    public Detail toDetail(History history, Member member){
        return new Detail(gameLog, preRating, postRating, result, history, member);
    }
}

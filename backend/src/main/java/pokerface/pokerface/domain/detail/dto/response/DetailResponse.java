package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.history.entity.GameMode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class DetailResponse {
    private final Long detailId;

    private final Long preRating;

    private final Long postRating;

    @Enumerated(EnumType.STRING)
    private Result result;

    private final Long historyId;

    private final GameMode gameMode;

    private final String opponentNickName;

    public static DetailResponse of(Detail detail){
        return DetailResponse.builder()
                .detailId(detail.getId())
                .preRating(detail.convertRatingToBounty(detail.getPreRating()))
                .postRating(detail.convertRatingToBounty(detail.getPostRating()))
                .result(detail.getResult())
                .historyId(detail.getHistory().getId())
                .gameMode(detail.getHistory().getGameMode())
                .opponentNickName((detail.getHistory().getHost().equals(detail.getMember()) ?
                        detail.getHistory().getGuest() : detail.getHistory().getHost()).getNickname())
                .build();
    }
}

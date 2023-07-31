package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.detail.entity.Result;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
public class DetailResponse {
    private final Long detailId;

    private final Integer preRating;

    private final Integer postRating;

    @Enumerated(EnumType.STRING)
    public Result result;

    private final GameLogResponse gameLogResponse;

    public static DetailResponse of(Detail detail, GameLogResponse gameLogResponse){
        return DetailResponse.builder()
                .detailId(detail.getId())
                .preRating(detail.getPreRating())
                .postRating(detail.getPostRating())
                .result(detail.getResult())
                .gameLogResponse(gameLogResponse)
                .build();
    }
}

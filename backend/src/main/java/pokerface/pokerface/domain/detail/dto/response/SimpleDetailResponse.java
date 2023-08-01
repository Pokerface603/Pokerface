package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.detail.entity.Result;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class SimpleDetailResponse {
    private final Integer preRating;

    private final Integer postRating;

    @Enumerated(EnumType.STRING)
    private Result result;

    public static SimpleDetailResponse of(Detail detail){
        return SimpleDetailResponse.builder()
                .preRating(detail.getPreRating())
                .postRating(detail.getPostRating())
                .result(detail.getResult())
                .build();
    }
}

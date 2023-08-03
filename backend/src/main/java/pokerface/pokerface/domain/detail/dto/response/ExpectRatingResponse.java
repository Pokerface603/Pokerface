package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;

@Builder
public class ExpectRatingResponse {
    private final Integer winRate;

    private final Integer loseRate;

    public static ExpectRatingResponse of(Integer winRate, Integer loseRate){
        return new ExpectRatingResponse(winRate, loseRate);
    }
}
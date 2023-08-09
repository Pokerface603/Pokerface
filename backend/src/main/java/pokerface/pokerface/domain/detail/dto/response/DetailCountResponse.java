package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DetailCountResponse {
    private Long totalCount;

    private Long winCount;

    private Long loseCount;

    private Integer winRate;

    public static DetailCountResponse of(Long totalCount, Long winCount){
        return DetailCountResponse.builder()
                .totalCount(totalCount)
                .winCount(winCount)
                .loseCount(totalCount - winCount)
                .winRate(Math.round((float)winCount / totalCount))
                .build();
    }
}

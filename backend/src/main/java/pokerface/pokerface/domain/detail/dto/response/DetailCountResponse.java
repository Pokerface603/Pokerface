package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.member.dto.response.RankingRes;
import pokerface.pokerface.domain.member.entity.Tier;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class DetailCountResponse {
    private Long totalCount;

    private Long winCount;

    private Long loseCount;

    private Integer winRate;

    private Long rating;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    public static DetailCountResponse of(Long totalCount, Long winCount, RankingRes rankingRes){
        return DetailCountResponse.builder()
                .totalCount(totalCount)
                .winCount(winCount)
                .loseCount(totalCount - winCount)
                .winRate(Math.round((float)winCount / totalCount * 100))
                .rating(rankingRes.getRating())
                .tier(rankingRes.getTier())
                .build();
    }
}

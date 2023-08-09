package pokerface.pokerface.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.entity.Tier;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class RankingRes {
    private final String nickname;

    private final Integer rating;

    @Enumerated(EnumType.STRING)
    private final Tier tier;

    public static RankingRes of(Member member){
        return new RankingRes(member.getNickname(), member.getRating(), member.getTier());
    }
}
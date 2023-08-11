package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.history.entity.PlayerType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Builder
@Getter
public class RoundLogResponse {
    @Enumerated(EnumType.STRING)
    private boolean isMeFirst;

    private final Integer myCardNum;

    private final Integer opponentCardNum;

    private final Integer myRemainChips;

    private final Integer opponentRemainChips;

    private final Integer seedMoney;

    private final List<TurnLogResponse> turnLogResponses;

    @Enumerated(EnumType.STRING)
    private Result result;

    public static RoundLogResponse of(PlayerType firstPlayer, Integer hostCardNum, Integer guestCardNum, Integer hostRemainChips, Integer guestRemainChips, Integer seedMoney, List<TurnLogResponse> turnLogResponses, Result result, boolean isHost){
        return RoundLogResponse.builder()
                .isMeFirst(isHost == firstPlayer.isValue())
                .myCardNum(isHost ? hostCardNum : guestCardNum)
                .opponentCardNum(isHost ? guestCardNum : hostCardNum)
                .myRemainChips(isHost ? hostRemainChips : guestRemainChips)
                .opponentRemainChips(isHost ? guestRemainChips : hostRemainChips)
                .seedMoney(seedMoney)
                .turnLogResponses(turnLogResponses)
                .result(isHost ? result : result.reverse())
                .build();
    }
}

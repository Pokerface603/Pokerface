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
    private PlayerType firstPlayer;

    private final Integer myCardNum;

    private final Integer opponentCardNum;

    private final Integer myRemainChips;

    private final Integer opponentRemainChips;

    private final Integer seedMoney;

    private final List<Integer> battingMoney;

    @Enumerated(EnumType.STRING)
    private Result result;

    public static RoundLogResponse of(PlayerType firstPlayer, Integer hostCardNum, Integer guestCardNum, Integer hostRemainChips, Integer guestRemainChips, Integer seedMoney, List<Integer> battingMoney, Result result, Boolean isHost){
        return RoundLogResponse.builder()
                .firstPlayer(firstPlayer)
                .myCardNum(isHost ? hostCardNum : guestCardNum)
                .opponentCardNum(isHost ? guestCardNum : hostCardNum)
                .myRemainChips(isHost ? hostRemainChips : guestRemainChips)
                .opponentRemainChips(isHost ? guestRemainChips : hostRemainChips)
                .seedMoney(seedMoney)
                .battingMoney(battingMoney)
                .result(isHost ? result : result.reverse())
                .build();
    }
}

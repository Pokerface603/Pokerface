package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import pokerface.pokerface.domain.detail.entity.Result;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Builder
public class RoundLogResponse {
    private final Integer hostCardNum;

    private final Integer guestCardNum;

    private final Integer hostRemainChips;

    private final Integer guestRemainChips;

    private final Integer seedMoney;

    private final List<Integer> battingMoney;

    @Enumerated(EnumType.STRING)
    private Result result;

    public static RoundLogResponse of(Integer hostCardNum, Integer guestCardNum, Integer hostRemainChips, Integer guestRemainChips, Integer seedMoney, List<Integer> battingMoney, Result result){
        return RoundLogResponse.builder()
                .hostCardNum(hostCardNum)
                .guestCardNum(guestCardNum)
                .hostRemainChips(hostRemainChips)
                .guestRemainChips(guestRemainChips)
                .seedMoney(seedMoney)
                .battingMoney(battingMoney)
                .result(result)
                .build();
    }
}

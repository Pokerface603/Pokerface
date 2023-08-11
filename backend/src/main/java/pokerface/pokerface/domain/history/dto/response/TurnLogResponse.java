package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.history.entity.BetType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class TurnLogResponse {
    @Enumerated(EnumType.STRING)
    private BetType betType;

    private final Integer bettingMoney;

    public static TurnLogResponse of(BetType betType, Integer bettingMoney){
        return new TurnLogResponse(betType, bettingMoney);
    }
}

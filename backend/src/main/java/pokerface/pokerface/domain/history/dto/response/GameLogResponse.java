package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GameLogResponse {
    private final List<RoundLogResponse> roundLogResponses;

    public static GameLogResponse of(List<RoundLogResponse> roundLogResponses){
        return new GameLogResponse(roundLogResponses);
    }
}

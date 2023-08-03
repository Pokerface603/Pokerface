package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import java.util.List;

@Builder
public class GameLogResponse {
    private final List<RoundLogResponse> roundLogResponses;

    public static GameLogResponse of(List<RoundLogResponse> roundLogResponses){
        return new GameLogResponse(roundLogResponses);
    }
}

package pokerface.pokerface.domain.detail.dto.response;

import lombok.Builder;
import java.util.List;

@Builder
public class GameLogResponse {
    public final List<RoundLogResponse> roundLogResponses;

    public static GameLogResponse of(List<RoundLogResponse> roundLogResponses){
        return new GameLogResponse(roundLogResponses);
    }
}

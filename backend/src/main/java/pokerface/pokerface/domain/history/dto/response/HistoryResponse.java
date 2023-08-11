package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.history.entity.History;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
public class HistoryResponse {
    private final Long historyId;

    @Enumerated(EnumType.STRING)
    private final GameMode gameMode;

    private final GameLogResponse gameLogResponse;

    private final String myNickName;

    private final String opponentNickName;

    @Enumerated(EnumType.STRING)
    private final Result result;

    public static HistoryResponse of(History history, GameLogResponse gameLogResponse, boolean isHost) {
        return HistoryResponse.builder()
                .historyId(history.getId())
                .gameMode(history.getGameMode())
                .gameLogResponse(gameLogResponse)
                .myNickName(isHost ? history.getHost().getNickname() : history.getGuest().getNickname())
                .opponentNickName(isHost ? history.getGuest().getNickname() : history.getHost().getNickname())
                .result(isHost ? history.getResult() : history.getResult().reverse())
                .build();
    }
}

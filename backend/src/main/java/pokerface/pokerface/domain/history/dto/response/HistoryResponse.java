package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import lombok.Getter;
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

    private final Long hostId;

    private final Long guestId;

    public static HistoryResponse of(History history, GameLogResponse gameLogResponse) {
        return HistoryResponse.builder()
                .historyId(history.getId())
                .gameMode(history.getGameMode())
                .gameLogResponse(gameLogResponse)
                .hostId(history.getHost().getId())
                .guestId(history.getGuest().getId())
                .build();
    }
}

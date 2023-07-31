package pokerface.pokerface.domain.history.dto.response;

import lombok.Builder;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.history.entity.History;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
public class HistoryResponse {
    private final Long historyId;

    @Enumerated(EnumType.STRING)
    private final GameMode gameMode;

    public static HistoryResponse of(History history) {
        return HistoryResponse.builder()
                .historyId(history.getId())
                .gameMode(history.getGameMode())
                .build();
    }
}

package pokerface.pokerface.domain.history.dto.request;

import lombok.Builder;
import lombok.Getter;
import pokerface.pokerface.domain.history.entity.History;

@Getter
public class HistoryRequest {
    @Builder.Default
    private String gameMode;

    private HistoryRequest(){}

    public History toHistory(){
        return new History(gameMode);
    }
}

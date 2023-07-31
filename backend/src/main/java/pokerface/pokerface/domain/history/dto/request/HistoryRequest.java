package pokerface.pokerface.domain.history.dto.request;

import pokerface.pokerface.domain.history.entity.History;

public class HistoryRequest {
    String gameMode;

    public History toHistory(){
        return new History(gameMode);
    }
}

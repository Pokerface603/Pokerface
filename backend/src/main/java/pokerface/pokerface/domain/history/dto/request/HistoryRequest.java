package pokerface.pokerface.domain.history.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {
    private Long hostId;

    private Long guestId;

    private String gameMode;

    private String gameLog;

    private String result;

    public History toHistory(Member host, Member guest){
        return new History(gameMode, gameLog, host, guest);
    }
}

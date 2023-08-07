package pokerface.pokerface.domain.room.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pokerface.pokerface.domain.history.entity.GameMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomInfoRes {
    private String sessionId;

    private GameMode gameMode;

    private String title;

    private Boolean isPrivate;

    private String roomPassword;

    private String hostName;

    private String hostTier;

    private int playerCount;
}

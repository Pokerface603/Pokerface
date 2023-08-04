package pokerface.pokerface.domain.room.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomInfoRes {
    private String sessionId;

    private String title;

    private Boolean isPrivate;

    private String roomPassword;

    private String hostName;

    private String hostTier;

    private int playerCount;
}

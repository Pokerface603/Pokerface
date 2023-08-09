package pokerface.pokerface.domain.room.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pokerface.pokerface.domain.history.entity.GameMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomCreateReq {
    private String title;

    private GameMode gameMode;

    private boolean isPrivate;

    private String roomPassword;
}

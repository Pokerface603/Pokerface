package pokerface.pokerface.domain.room.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindByGameModeAndTitle {
    private int pageNum;

    private String gameMode;

    private String title;
}

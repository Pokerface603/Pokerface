package pokerface.pokerface.domain.room.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomPageInfoRes {
    private int totalPageCount;

    private List<RoomInfoRes> roomInfoResList;
}

package pokerface.pokerface.domain.match.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.member.service.MemberService;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;
import pokerface.pokerface.domain.room.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MemberService memberService;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    public RoomInfoRes findRoomByRating(Long memberId) {
        Integer rating = memberService.findById(memberId).getRating();

        for(int times = 1; times <= 6; times++){
            List<Room> rooms = findRoomsByMaxRatingAndMinRating(rating + 50 * times, Math.max(0, rating - 50 * times));

            try {
                if(rooms.isEmpty()){
                    Thread.sleep(3000);
                    continue;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RestException(ErrorCode.MATCHING_FAILED);
            }
            return roomService.findRoomInfoById(findProximateRoom(rooms, rating));
        }
        throw new RestException(ErrorCode.MATCHING_FAILED);
    }

    public String findProximateRoom(List<Room> rooms, Integer rating){
        int gap = Integer.MAX_VALUE;
        Room target = null;

        for(Room room : rooms){
            int now = Math.abs(room.getMembers().get(0).getRating() - rating);
            if(gap > now){
                gap = now;
                target = room;
            }
        }
        assert target != null;
        return target.getSessionId();
    }
    public List<Room> findRoomsByMaxRatingAndMinRating(Integer maxRating, Integer minRating){
        Iterable<Room> rooms = roomRepository.findAll();

        return StreamSupport.stream(rooms.spliterator(), false)
                .filter(room -> room.getMembers().size() == 1)
                .filter(room -> room.getMembers().get(0).getRating() < maxRating)
                .filter(room -> room.getMembers().get(0).getRating() > minRating)
                .collect(Collectors.toList());
    }
}
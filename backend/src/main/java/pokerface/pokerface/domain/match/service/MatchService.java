package pokerface.pokerface.domain.match.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokerface.pokerface.domain.member.service.MemberService;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MemberService memberService;
    private final RoomRepository roomRepository;

    public String findRoomByRating(Long memberId) throws InterruptedException {
        Integer rating = memberService.findById(memberId).getRating();

        for(int times = 1; times <= 6; times++){
            List<Room> rooms = findRoomsByMaxRatingAndMinRating(rating + 50 * times, Math.max(0, rating - 50 * times));
            if(rooms.isEmpty()){
                Thread.sleep(3000);
                continue;
            }
            return findProximateRoom(rooms, rating);
        }
        return "";
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
                .filter(room -> room.getMembers().get(0).getRating() < maxRating)
                .filter(room -> room.getMembers().get(0).getRating() > minRating)
                .collect(Collectors.toList());
    }
}
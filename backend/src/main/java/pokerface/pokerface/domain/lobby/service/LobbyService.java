package pokerface.pokerface.domain.lobby.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.friend.service.FriendService;
import pokerface.pokerface.domain.lobby.dto.response.LobbyFriendsResponse;
import pokerface.pokerface.domain.lobby.dto.response.LobbyResponse;
import pokerface.pokerface.domain.member.service.MemberService;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;
import pokerface.pokerface.domain.room.service.RoomService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class LobbyService {

    private final MemberService memberService;
    private final RoomService roomService;
    private final FriendService friendService;

    private final RoomRepository roomRepository;

    public String findNickNameByEmail(String email){
        return memberService.findByEmail(email).getNickname();
    }

    public void saveFriends(String firstEmail, String secondEmail){
        friendService.save(firstEmail, secondEmail);
    }

    public RoomInfoRes findRoomByRating(String email) {
        Integer rating = memberService.findByEmail(email).getRating();

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

    public List<LobbyFriendsResponse> friendsList(List<String> emails, String memberEmail){
        return friendService.findFriendsByFromEmail(memberEmail).stream()
                .filter(emails::contains)
                .map(s -> memberService.findByEmail(s).getNickname())
                .map(s -> LobbyFriendsResponse.of(s,
                        roomService.findAllRoomInfos().stream()
                            .filter(roomInfoRes -> roomInfoRes.getHostName().equals(s))
                            .findFirst().orElse(null)))
                .collect(Collectors.toList());
    }

    public List<LobbyResponse> getConnectionMembers(List<String> connections, String email){
        List<String> friends = friendService.findFriendsByFromEmail(email);

        return memberService.findByEmails(connections).stream()
                .map(memberLoginRes -> LobbyResponse.of(memberLoginRes,
                        friends.contains(memberLoginRes.getEmail())))
                .collect(Collectors.toList());
    }
}
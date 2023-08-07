package pokerface.pokerface.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.entity.Tier;
import pokerface.pokerface.domain.member.repository.MemberRepository;
import pokerface.pokerface.domain.room.dto.request.RoomCreateReq;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    public RoomInfoRes findRoomInfoById(String sessionId) {
        Room room = roomRepository.findById(sessionId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));

        return RoomInfoRes.builder()
                .sessionId(room.getSessionId())
                .gameMode(room.getGameMode())
                .title(room.getTitle())
                .isPrivate(room.getIsPrivate())
                .roomPassword(room.getRoomPassword())
                .hostName(room.getMembers().get(0).getNickname())
                .hostTier(room.getMembers().get(0).getTier().toString())
                .playerCount(room.getMembers().size())
                .build();
    }

    public List<RoomInfoRes> findAllRoomInfos() {
        Iterable<Room> rooms = roomRepository.findAll();

        return StreamSupport.stream(rooms.spliterator(), false)
                .map(room -> RoomInfoRes.builder()
                    .gameMode(room.getGameMode())
                    .sessionId(room.getSessionId())
                    .title(room.getTitle())
                    .isPrivate(room.getIsPrivate())
                    .roomPassword(room.getRoomPassword())
                    .hostName(room.getMembers().get(0).getNickname())
                    .hostTier(room.getMembers().get(0).getTier().toString())
                    .playerCount(room.getMembers().size())
                    .build())
                        .collect(Collectors.toList());
    }

    @Transactional
    public Room createRoom(String sessionId, RoomCreateReq roomCreateReq) {
        // 로그인이 구현되어 있지 않아 방 생성 시 임의로 사용자를 넣어준다.
        Member member = new Member("test1", "1234", "jio", Tier.ACE);

        return roomRepository.save(Room.builder()
                .sessionId(sessionId)
                .gameMode(roomCreateReq.getGameMode())
                .title(roomCreateReq.getTitle())
                .isPrivate(roomCreateReq.isPrivate())
                .roomPassword(roomCreateReq.getRoomPassword())
                .members(List.of(member))
                .build());
    }

    @Transactional
    public void removeRoom(String sessionId) {
        roomRepository.deleteById(sessionId);
    }

    @Transactional
    public void removeMember(String sessionId, String email) {
        roomRepository.findById(sessionId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND))
                .getMembers().remove(memberRepository.findByEmail(email)
                        .orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND))
        );
    }

    public List<RoomInfoRes> findRoomsByGameMode(GameMode gameMode) {
        return roomRepository.findAllByGameMode(gameMode).stream()
                .map(room -> RoomInfoRes.builder()
                        .gameMode(room.getGameMode())
                        .sessionId(room.getSessionId())
                        .title(room.getTitle())
                        .isPrivate(room.getIsPrivate())
                        .roomPassword(room.getRoomPassword())
                        .hostName(room.getMembers().get(0).getNickname())
                        .hostTier(room.getMembers().get(0).getTier().toString())
                        .playerCount(room.getMembers().size())
                        .build())
                .collect(Collectors.toList());
    }

    /*
    Redis는 sql의 like와 같은 패턴 매칭 기능이 없어 전체 방을 조회 후 stream으로 검색어(방 제목)를 포함하는 여부 확인
     */
    public List<RoomInfoRes> findRoomsByTitle(String title) {
        return findAllRoomInfos().stream()
                .filter(roomInfoRes -> roomInfoRes.getTitle().contains(title))
                .collect(Collectors.toList());
    }
}

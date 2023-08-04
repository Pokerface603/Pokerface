package pokerface.pokerface.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.entity.Tier;
import pokerface.pokerface.domain.member.repository.MemberRepository;
import pokerface.pokerface.domain.room.dto.request.RoomCreateReq;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;

import java.util.ArrayList;
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
                .getMembers().remove(memberRepository.findMemberByEmail(email)
                        .orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND))
        );
    }
}

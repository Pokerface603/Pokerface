package pokerface.pokerface.domain.room.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.entity.Tier;
import pokerface.pokerface.domain.member.repository.MemberRepository;
import pokerface.pokerface.domain.room.dto.request.RoomCreateReq;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.repository.RoomRepository;
import pokerface.pokerface.domain.room.service.RoomService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RoomTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void saveRoom() {
        Member member1 = new Member("test1", "1234", "jio", Tier.ACE);
        Member member2 = new Member("test2", "1234", "seo", Tier.KING);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = new ArrayList<>(List.of(member1, member2));

        String sessionId = "sessionD";

        Room room = Room.builder().gameMode(GameMode.NORMAL).title("안녕하세요").roomPassword("1234").isPrivate(true)
                .sessionId(sessionId).members(members).build();

        Room savedRoom = roomRepository.save(room);

        assertThat(room).isEqualTo(savedRoom);

        System.out.println("savedRoom = " + savedRoom);
    }

    @Test
    @Transactional
    void createRoom() {
        Room createdRoom = roomService.createRoom("sessionB", memberRepository.findById(3L).orElse(null),
                new RoomCreateReq("하이하이", GameMode.NORMAL, false, null));

        System.out.println("createdRoom = " + createdRoom);
    }

    @Test
    @Transactional
    void getRoomInfo() {
        String sessionId = "sessionA";
        System.out.println(roomService.findRoomInfoById(sessionId));
    }

    @Test
    @Transactional
    void deleteRoomInfo() {
        String sessionId = "sessionA";
        roomService.removeRoom(sessionId);
        assertThat(roomRepository.findById(sessionId).orElse(null)).isNull();
    }

    @Test
    @Transactional
    void removeMember() {
        String sessionId = "sessionC";

        Room findRoom = roomRepository.findById(sessionId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
        System.out.println("findRoom = " + findRoom);

        roomService.removeMember(sessionId, memberRepository.findById(4L)
                .orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND)));

        System.out.println("roomInfo = " + roomRepository.findById(sessionId)
                .orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND)));
    }

    @Test
    @Transactional
    void findRoomsByMode() {
        List<RoomInfoRes> roomsByMode = roomService.findByGameMode(GameMode.NORMAL);
        System.out.println("roomsByMode.size() = " + roomsByMode.size());

        roomService.findByGameMode(GameMode.NORMAL).forEach(System.out::println);
        }

    @Test
    @Transactional
    void findRoomByTitle() {
        Room room = roomRepository.findByTitle("hello");
        System.out.println("room = " + room);
    }

    @Test
    @Transactional
    void findAllRoomByTitle() {
        roomService.findRoomsByTitle("안녕").forEach(System.out::print);
    }

    @Test
    @Transactional
    void joinRoom() {
        String sessionId = "sessionC";
        Member member = memberRepository.findById(4L).orElse(null);
        System.out.println("member = " + member);
        roomService.joinRoom(sessionId, member);
    }
}
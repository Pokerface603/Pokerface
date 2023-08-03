package pokerface.pokerface.domain.room.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.room.dto.request.RoomCreateReq;
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

    @Test
    @Transactional
    void saveRoom() {
        Member member1 = new Member("test1", "1234", "jio");
        Member member2 = new Member("test2", "1234", "seo");

        List<Member> members = new ArrayList<>(List.of(member1, member2));


        Room room = Room.builder().title("테스트용 방").roomPassword(null).isPrivate(false)
                .sessionId("sessionA").members(members).build();

        Room savedRoom = roomRepository.save(room);

        assertThat(room).isEqualTo(savedRoom);

        System.out.println("savedRoom = " + savedRoom);
    }

    @Test
    @Transactional
    void createRoom() {
        Member member1 = new Member("test1", "1234", "jio");
        Member member2 = new Member("test2", "1234", "seo");

        List<Member> members = new ArrayList<>(List.of(member1, member2));

        Room room = Room.builder().title("하이하이").roomPassword("1234").isPrivate(true)
                .sessionId("sessionA").members(members).build();

        Room createdRoom = roomService.createRoom("sessionA",
                new RoomCreateReq("하이하이", GameMode.ORIGIN, false, "1234"));

        System.out.println("createdRoom = " + createdRoom);

        assertThat(room).isNotEqualTo(createdRoom);
    }

    @Test
    @Transactional
    void removeMember() {
        String sessionId = "sessionA";
        Room roomBySessionId = roomRepository.findRoomBySessionId(sessionId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
        System.out.println("roomBySessionId = " + roomBySessionId);

        roomService.removeMember(sessionId, "test1");

        System.out.println("roomInfo = " + roomRepository.findRoomBySessionId(sessionId));
    }
}
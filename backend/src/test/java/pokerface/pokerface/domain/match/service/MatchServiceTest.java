package pokerface.pokerface.domain.match.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.lobby.service.LobbyService;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.repository.MemberRepository;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MatchServiceTest {

    @Autowired
    private LobbyService lobbyService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void findRoom() throws InterruptedException{
        Member member1 = new Member("test1", "aaa", "1234", 1200);
        Member member2 = new Member("test2", "bbb", "1234", 1300);
        Member member3 = new Member("test3", "ccc", "1234", 1100);

        // 4번 멤버로 방을 검색
        Member member4 = new Member("test4", "ddd", "1234", 1500);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        Room room1 = Room.builder().gameMode(GameMode.NORMAL).title("레이팅 1200 방")
                .sessionId("rating1200").members(new ArrayList<>(List.of(member1))).build();
        Room room2 = Room.builder().gameMode(GameMode.NORMAL).title("레이팅 1300 방")
                .sessionId("rating1300").members(new ArrayList<>(List.of(member2))).build();
        Room room3 = Room.builder().gameMode(GameMode.NORMAL).title("레이팅 1100 방")
                .sessionId("rating1100").members(new ArrayList<>(List.of(member3))).build();

        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);

        System.out.println(lobbyService.findRoomByRating(member4.getEmail()));
    }
}

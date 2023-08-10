package pokerface.pokerface.domain.lobby.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokerface.pokerface.domain.friend.service.FriendService;
import pokerface.pokerface.domain.member.service.MemberService;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LobbyService {

    private final MemberService memberService;

    private final FriendService friendService;


    public String findNickNameByEmail(String email){
        return memberService.findByEmail(email).getNickname();
    }

    public void saveFriends(String firstEmail, String secondEmail){
        friendService.save(firstEmail, secondEmail);
    }
}

package pokerface.pokerface.domain.lobby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pokerface.pokerface.domain.lobby.handler.LobbyHandler;
import pokerface.pokerface.domain.member.dto.response.MemberLoginRes;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/lobbies")
@RequiredArgsConstructor
@Log4j2
public class LobbyController {

    private final LobbyHandler lobbyHandler;
    private final MemberService memberService;

    @GetMapping
    public List<MemberLoginRes> getConnectionMembers(){
        return memberService.findByEmails(lobbyHandler.connectionMemberList());
    }
}

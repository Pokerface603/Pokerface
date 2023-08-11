package pokerface.pokerface.domain.lobby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pokerface.pokerface.domain.lobby.handler.LobbyHandler;
import pokerface.pokerface.domain.lobby.service.LobbyService;
import pokerface.pokerface.domain.member.dto.response.MemberLoginRes;
import pokerface.pokerface.domain.member.service.MemberService;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;

import java.util.List;

@RestController
@RequestMapping("/lobbies")
@RequiredArgsConstructor
@Log4j2
public class LobbyController {

    private final LobbyHandler lobbyHandler;
    private final MemberService memberService;
    private final LobbyService lobbyService;

    @GetMapping
    public List<MemberLoginRes> getConnectionMembers(){
        return memberService.findByEmails(lobbyHandler.connectionMemberList());
    }

    @GetMapping("/match/{email}")
    public ResponseEntity<RoomInfoRes> findRoomByRating(@PathVariable String email){
        return new ResponseEntity<>(lobbyService.findRoomByRating(email), HttpStatus.OK);
    }
}

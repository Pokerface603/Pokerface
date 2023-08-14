package pokerface.pokerface.domain.lobby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pokerface.pokerface.domain.lobby.dto.response.LobbyFriendsResponse;
import pokerface.pokerface.domain.lobby.dto.response.LobbyResponse;
import pokerface.pokerface.domain.lobby.handler.LobbyHandler;
import pokerface.pokerface.domain.lobby.service.LobbyService;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;

import java.util.List;

@RestController
@RequestMapping("/lobbies")
@RequiredArgsConstructor
@Log4j2
public class LobbyController {

    private final LobbyHandler lobbyHandler;
    private final LobbyService lobbyService;

    @GetMapping("/{email}")
    public ResponseEntity<List<LobbyResponse>> getConnectionMembers(@PathVariable String email){
        return new ResponseEntity<>(lobbyService.getConnectionMembers(lobbyHandler.connectionMemberList(), email), HttpStatus.OK);
    }

    @GetMapping("/friend/{email}")
    public ResponseEntity<List<LobbyFriendsResponse>> getConnectionFriends(@PathVariable String email){
        return new ResponseEntity<>(lobbyService.friendsList(lobbyHandler.connectionMemberList(), email), HttpStatus.OK);
    }

    @GetMapping("/match/{email}")
    public ResponseEntity<RoomInfoRes> findRoomByRating(@PathVariable String email){
        return new ResponseEntity<>(lobbyService.findRoomByRating(email), HttpStatus.OK);
    }
}

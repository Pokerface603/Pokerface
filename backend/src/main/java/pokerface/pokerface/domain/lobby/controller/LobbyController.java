package pokerface.pokerface.domain.lobby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobbies")
@RequiredArgsConstructor
@Log4j2
public class LobbyController {

    @GetMapping
    public String chatGet(){
        log.info("@lobbyController, lobby GET()");

        return "lobby";
    }
}

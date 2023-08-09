package pokerface.pokerface.domain.match.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pokerface.pokerface.domain.match.service.MatchService;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;
    private final RoomService roomService;

    @GetMapping("/{memberId}")
    public ResponseEntity<RoomInfoRes> findRoomByRating(@PathVariable Long memberId){
        return new ResponseEntity<>(matchService.findRoomByRating(memberId), HttpStatus.OK);
    }
}
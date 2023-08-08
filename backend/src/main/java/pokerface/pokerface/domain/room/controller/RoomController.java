package pokerface.pokerface.domain.room.controller;

import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.config.login.PrincipalDetails;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    /**
     * 모든 방 정보 조회
     */
    @GetMapping
    public ResponseEntity<List<RoomInfoRes>> findAllRoomInfos() {
        return new ResponseEntity<>(roomService.findAllRoomInfos(), HttpStatus.OK);
    }

    /**
     * 세션 아이디로 방 정보 조회
     */
    @GetMapping("/{sessionId}")
    public ResponseEntity<RoomInfoRes> findRoomInfoById(@PathVariable String sessionId) {
        return new ResponseEntity<>(roomService.findRoomInfoById(sessionId), HttpStatus.OK);
    }

    /**
     * 게임 모드로만 방 검색
     */
    @GetMapping("/mode/{gameMode}")
    public ResponseEntity<List<RoomInfoRes>> findAllRoomInfosByGameMode(@PathVariable String gameMode) {
        return new ResponseEntity<>(roomService.findRoomsByGameMode(GameMode.valueOf(gameMode)), HttpStatus.OK);
    }

    /**
     * 제목으로만 방 검색
     */
    @GetMapping("/title/{title}")
    public ResponseEntity<List<RoomInfoRes>> findAllRoomInfosByTitle(@PathVariable String title) {
        return new ResponseEntity<>(roomService.findRoomsByTitle(title), HttpStatus.OK);
    }

    /**
     * 게임 모드와 제목으로 방 검색
     */
    @GetMapping("/mode/{gameMode}/title/{title}")
    public ResponseEntity<List<RoomInfoRes>> findAllRoomInfosByGameModeAndTitle(@PathVariable String gameMode,
                                                                                @PathVariable String title) {
        return new ResponseEntity<>(
                roomService.findRoomsByGameModeAndTitle(GameMode.valueOf(gameMode), title), HttpStatus.OK);
    }

    /**
     * 방 삭제
     */
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String sessionId) {
        roomService.deleteRoom(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 방장이 강제 퇴장시키는 경우
     */
    @PostMapping("/{sessionId}/{email}")
    public ResponseEntity<Void> removeMember(@PathVariable String sessionId,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {
        roomService.removeMember(sessionId, principalDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 참가자가 스스로 나간 경우
     */
    @PostMapping("/{sessionId}/{email}/disconnections")
    public ResponseEntity<Void> disConnection(@PathVariable("sessionId") String sessionId,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails) {
        roomService.removeMember(sessionId, principalDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

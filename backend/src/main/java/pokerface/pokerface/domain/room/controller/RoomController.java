package pokerface.pokerface.domain.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.config.login.PrincipalDetails;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.room.dto.request.DisconnectReq;
import pokerface.pokerface.domain.room.dto.request.FindByGameModeAndTitle;
import pokerface.pokerface.domain.room.dto.request.FindByGameModeReq;
import pokerface.pokerface.domain.room.dto.response.RoomInfoRes;
import pokerface.pokerface.domain.room.dto.response.RoomPageInfoRes;
import pokerface.pokerface.domain.room.entity.Room;
import pokerface.pokerface.domain.room.repository.RoomRepository;
import pokerface.pokerface.domain.room.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RoomRepository roomRepository;

    /**
     * 모든 방 정보 조회
     */
    @GetMapping()
    public ResponseEntity<List<RoomInfoRes>> findAllRoomInfos() {
        return new ResponseEntity<>(roomService.findAllRoomInfos(), HttpStatus.OK);
    }

    /**
     * 세션 아이디로 방 정보 조회
     */
    @GetMapping("/{sessionId}")
    public ResponseEntity<Room> findRoomInfoById(@PathVariable String sessionId) {
        return new ResponseEntity<>(roomRepository.findById(sessionId).orElseGet(null), HttpStatus.OK);
    }

    /**
     * 게임 모드로만 방 검색
     */
    @GetMapping("/findByGameMode")
    public ResponseEntity<RoomPageInfoRes> findByGameMode(@RequestBody FindByGameModeReq req) {
        return new ResponseEntity<>(RoomPageInfoRes.builder()
                .totalPageCount(roomService.findByGameModeRoomsCount(GameMode.valueOf(req.getGameMode())))
                .roomInfoResList(roomService.findByGameModeWithPaging(
                        GameMode.valueOf(req.getGameMode()), req.getPageNum())).build(), HttpStatus.OK);
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
    @GetMapping("/findByGameModeAndTitle")
    public ResponseEntity<RoomPageInfoRes> findByGameModeAndTitle(@RequestBody FindByGameModeAndTitle req) {
        return new ResponseEntity<>(RoomPageInfoRes.builder()
                .totalPageCount(roomService.findByGameModeAndTitleRoomsCount(
                        GameMode.valueOf(req.getGameMode()), req.getTitle()))
                .roomInfoResList(roomService.findByGameModeAndTitleWithPaging(
                        GameMode.valueOf(req.getGameMode()), req.getTitle(), req.getPageNum()))
                .build(), HttpStatus.OK);
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
     * 참가자가 스스로 나간 경우
     */
    @PostMapping("/disconnect")
    public ResponseEntity<Void> disConnect(@RequestBody DisconnectReq disconnectReq) {
        roomService.removeMember(disconnectReq.getSessionId(), disconnectReq.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

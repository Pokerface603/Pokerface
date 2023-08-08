package pokerface.pokerface.domain.openvidu.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;

import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.config.login.PrincipalDetails;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.room.dto.request.RoomCreateReq;
import pokerface.pokerface.domain.room.service.RoomService;

@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class OpenviduController {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    private final RoomService roomService;

    @PostMapping("/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params,
                                                    @AuthenticationPrincipal PrincipalDetails principalDetails)
            throws OpenViduJavaClientException, OpenViduHttpException {

        log.debug(principalDetails.getMember().toString());

        SessionProperties properties = SessionProperties.fromJson(params).build();
        Session session = openvidu.createSession(properties);

        roomService.createRoom(session.getSessionId(), principalDetails.getMember(), // 방 생성
                RoomCreateReq.builder()
                        .title((String) params.get("title"))
                        .gameMode(GameMode.valueOf((String) params.get("gameMode")))
                        .isPrivate((boolean) params.get("isPrivate"))
                        .roomPassword((String) params.get("roomPassword"))
                        .build());

        return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
    }


    @GetMapping("/sessions")
    public ResponseEntity<?> getActiveSessions(){
        System.out.println("openvidu.getActiveSessions().size() = " + openvidu.getActiveSessions().size());
        openvidu.getActiveSessions().
                forEach(session -> System.out.println("session.getSessionId() = " + session.getSessionId()));

        return new ResponseEntity<>(openvidu.getActiveSessions(), HttpStatus.OK);
    }

    @PostMapping("/sessions/{sessionId}/connections")
    public ResponseEntity<String> createConnection(@PathVariable("sessionId") String sessionId,
                                                   @RequestBody(required = false) Map<String, Object> params,
                                                   @AuthenticationPrincipal PrincipalDetails principalDetails)
            throws OpenViduJavaClientException, OpenViduHttpException {

        String inputPassword = (String) params.get("password");
        log.debug("비밀번호 검증 : {}", inputPassword);
        if(!inputPassword.equals("")) { // 비빌번호가 있는 방에 참가하려는 경우
            if(!roomService.findRoomById(sessionId).getRoomPassword().equals(inputPassword)) { // 비밀번호 불일치
                throw new RestException(ErrorCode.INVALID_PASSWORD);
            }
        }

        Session session = openvidu.getActiveSession(sessionId);
        log.debug("방 인원 검증 : {}", session.getConnections().size());
        if (session.getConnections().size() >= 2) { // 방에 두 명 넘게 접속을 시도할 경우
            throw new RestException(ErrorCode.ALREADY_FULL);
        }

        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);

        log.debug("새로운 인원 참가에 인한 방 정보 갱신");
        roomService.joinRoom(sessionId, principalDetails.getMember()); // 방 정보 갱신

        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
    }
}


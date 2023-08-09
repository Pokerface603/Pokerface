package pokerface.pokerface.domain.lobby.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class LobbyHandler extends TextWebSocketHandler {

    // 추후 jwt 토큰 인증 후 토큰에 있는 사용자 정보를 저장하는 Map 형태로 변경 예정
    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        for(WebSocketSession sess: list) {
            sess.sendMessage(message);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        list.add(session);
        log.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.info(session + " 클라이언트 접속 해제");
        list.remove(session);
    }

    /* 인터셉트한 HttpSession에서 현재 접속한 멤버의 정보를 획득 후 반환하는 메서드*/
    public List<String> connectionMemberList(){
        return list.stream().map(webSocketSession -> webSocketSession.getPrincipal().getName())
                .collect(Collectors.toList());
    }
}

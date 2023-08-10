package pokerface.pokerface.domain.lobby.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.lobby.entity.MessageType;
import pokerface.pokerface.domain.lobby.service.LobbyService;

import java.util.*;

@Component
@Log4j2
@RequiredArgsConstructor
public class LobbyHandler extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> sessions = new HashMap<>();

    private final LobbyService lobbyService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("message : " + payload);
        String[] strs = payload.split(",");

        try {
            if(strs.length == 2){
                WebSocketSession target = sessions.get(strs[1]);
                String addMessage = "";
                String nickName = lobbyService.findNickNameByEmail(session.getPrincipal().getName());

                switch (MessageType.valueOf(strs[0])){
                    case REQUEST:
                        addMessage += "님이 친구요청을 보냈습니다. ";
                        break;
                    case RESPONSE:
                        addMessage += "님이 친구요청을 수락했습니다. ";
                        lobbyService.saveFriends(session.getPrincipal().getName(), target.getPrincipal().getName());
                        break;
                    default:
                        throw new RestException(ErrorCode.WEBSOCKET_MESSAGE_ERROR);
                }
                TextMessage msg = new TextMessage(nickName + addMessage);
                target.sendMessage(msg);
            }
        }catch (Exception e){
            throw new RestException(ErrorCode.WEBSOCKET_MESSAGE_ERROR);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sessions.put(session.getPrincipal().getName(), session);
        log.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.info(session + " 클라이언트 접속 해제");

        sessions.remove(session.getPrincipal().getName());
    }

    /* 인터셉트한 HttpSession에서 현재 접속한 멤버의 정보를 획득 후 반환하는 메서드*/
    public List<String> connectionMemberList(){
        return list.stream().map(webSocketSession -> webSocketSession.getPrincipal().getName())
                .collect(Collectors.toList());
    }
}

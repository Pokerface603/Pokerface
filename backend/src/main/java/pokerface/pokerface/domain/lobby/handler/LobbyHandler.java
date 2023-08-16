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

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class LobbyHandler extends TextWebSocketHandler {

    private static Map<WebSocketSession, String> sessions = new HashMap<>();

    private final LobbyService lobbyService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("message : " + payload);
        String[] splitMessage = payload.split(",");
        String sessionEmail = sessions.getOrDefault(session, "");

        if(splitMessage.length != 2 || sessionEmail.equals(splitMessage[1])) {
            throw new RestException(ErrorCode.WEBSOCKET_MESSAGE_ERROR);
        }

        switch (MessageType.valueOf(splitMessage[0])){
            case CONNECT:
                addSessions(session, splitMessage[1]);
                break;

            case RESPONSE:
                lobbyService.saveFriends(sessionEmail, splitMessage[1]);

            case REQUEST:
                String nickName = lobbyService.findNickNameByEmail(sessionEmail);
                TextMessage msg = new TextMessage(splitMessage[0] + "," + sessionEmail + "," + nickName);

                for(WebSocketSession target : sessions.keySet()){
                    if(sessions.get(target).equals(splitMessage[1])){
                        target.sendMessage(msg);
                        return;
                    }
                }

            default:
                throw new RestException(ErrorCode.WEBSOCKET_MESSAGE_ERROR);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        log.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {

        log.info(session + " 클라이언트 접속 해제");

        sessions.remove(session);
        sendUpdateMessage();
    }

    public void addSessions(WebSocketSession session, String email) throws IOException{
        sessions.put(session, email);

        sendUpdateMessage();
    }

    /* 인터셉트한 HttpSession에서 현재 접속한 멤버의 정보를 획득 후 반환하는 메서드*/
    public List<String> connectionMemberList(){
        return sessions.keySet().stream()
                .map(sessions::get)
                .distinct()
                .collect(Collectors.toList());
    }

    public void sendUpdateMessage() throws IOException {
        TextMessage msg = new TextMessage(MessageType.UPDATE.toString());

        for(WebSocketSession session : sessions.keySet()){
            session.sendMessage(msg);
        }
    }
}
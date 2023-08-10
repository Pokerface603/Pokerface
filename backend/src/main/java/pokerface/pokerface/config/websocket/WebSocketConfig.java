package pokerface.pokerface.config.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import pokerface.pokerface.domain.lobby.handler.LobbyHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final LobbyHandler lobbyHandler;

    /* 웹소켓 핸드셰이킹 과정에서 HttpSession을 인터셉트 할 수 있는 HttpSessionHandshakeInterceptor 추가*/
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(lobbyHandler, "ws/lobby").addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
    }
}
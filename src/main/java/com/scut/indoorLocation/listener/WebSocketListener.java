package com.scut.indoorLocation.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by Mingor on 2020/4/19 22:28
 */
@Component
@Slf4j
public class WebSocketListener {

    @EventListener
    public void handleConnectListener(SessionConnectedEvent event) {
//        log.info("websocket connect");
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    }

    @EventListener
    public void handleDisconnectListener(SessionDisconnectEvent event) {
//        log.info("websocket disconnect");
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    }

}

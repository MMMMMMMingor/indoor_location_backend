package com.scut.indoorLocation.listener;

import com.scut.indoorLocation.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2020/4/19 22:28
 */
@Component
@Slf4j
public class WebSocketListener{

    @Resource
    private JwtUtil jwtUtil;

    @EventListener
    public void handleConnectListener(SessionConnectedEvent event) {
        log.info(" socket connect");
        // do someting ...
    }

    @EventListener
    public void handleDisconnectListener(SessionDisconnectEvent event) {
        log.info("socket disconnect");
        // do someting ...
    }

}

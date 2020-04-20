package com.scut.indoorLocation.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by Mingor on 2020/4/12 22:27
 */
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/location").setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 设置接收客户端消息 的 路径前缀（不设置可以）
//        registry.setApplicationDestinationPrefixes("/app");
        // 设置接收客户端订阅 的 路径前缀（必须不设置，可以为空串）
//        registry.enableSimpleBroker("topic");
    }

}

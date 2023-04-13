package com.june.chatdemo.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");  // 메시지 구독 요청 prefix
        config.setApplicationDestinationPrefixes("/pub");   // 메시지 발행 요청 prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp websocket endpoint 설정( ws://localhost:8080/ws-stomp )
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("http://localhost:*").withSockJS();
    }
}

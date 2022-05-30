package io.suriya.sms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

//@Configuration
//@EnableWebSocketMessageBroker
@Slf4j
public class AppConfig {

	//@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		log.info("Inside configureMessageBroker");
		config.enableSimpleBroker("/lesson");
		config.setApplicationDestinationPrefixes("/app");
	}

	//@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		log.info("Inside registerStompEndpoints");
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
	}
}

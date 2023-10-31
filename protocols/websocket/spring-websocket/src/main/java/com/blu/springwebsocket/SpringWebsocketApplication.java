package com.blu.springwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringWebsocketApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringWebsocketApplication.class);
	public static void main(String[] args) {
		logger.info("Websocket example with STOMP!!");
		SpringApplication.run(SpringWebsocketApplication.class, args);
	}

}

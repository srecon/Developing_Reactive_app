package com.blu.reactive.rsocket.client;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MimeTypeUtils;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.time.Duration;

@Configuration
public class ClientConfiguration {
//    @Bean
//    public RSocketRequester getRSocketRequester(){
//
//        RSocketRequester.Builder builder = RSocketRequester.builder();
//
//        return builder
//                .rsocketConnector(
//                        rSocketConnector ->
//                                rSocketConnector.reconnect(
//                                        RabbitProperties.Retry.fixedDelay(2, Duration.ofSeconds(2))
//                                )
//                )
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
//                .tcp("localhost", 7000);
//    }
}

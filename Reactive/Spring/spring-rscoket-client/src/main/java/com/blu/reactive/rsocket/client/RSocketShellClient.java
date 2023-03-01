package com.blu.reactive.rsocket.client;

import com.blu.reactive.rsocket.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.MimeTypeUtils;

import java.util.UUID;

@Slf4j
@ShellComponent
public class RSocketShellClient {
    private RSocketRequester rsocketRequester;

    private static final String CLIENT = "Client";
    private static final String REQUEST = "Request";

    @Bean
    public RSocketRequester getRSocketRequester() {

        String client = UUID.randomUUID().toString();
        log.info("Connecting using client ID: {}", client);

        RSocketRequester.Builder builder = RSocketRequester.builder();

        return this.rsocketRequester = builder
                                            .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                                            .connectTcp("localhost",7007).block();
    }
//    @Autowired
//    public RSocketShellClient(RSocketRequester.Builder rsocketRequesterBuilder) {
//
//
//        this.rsocketRequester = rsocketRequesterBuilder.connectTcp("localhost",7007).block();
//    }
    @ShellMethod("Send one request. One response will be printed.")
    public void requestResponse() throws InterruptedException {
        log.info("\nSending one request. Waiting for one response...");
        Message message = this.rsocketRequester
                .route("request-response")
                .data(new Message(CLIENT, REQUEST))
                .retrieveMono(Message.class)
                .block();
        log.info("\nResponse was: {}", message);
    }
}

package com.blu.reactive.rsocket.server;

import com.blu.reactive.rsocket.server.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class RSocketController {
    private static final String SERVER = "Server";
    private static final String RESPONSE = "Response";
    private static final String STREAM = "streaming";

    private final List<RSocketRequester> CLIENTS = new ArrayList<>();

    @MessageMapping("request-response")
    Message requestResponse(Message request) {
       log.info("Received request-response request: {}", request);
       return new Message(SERVER, RESPONSE);
    }
    @MessageMapping("fire-and-forget")
    public void fireAndForget(Message request){
        log.info("Received fire&forget request {}", request);
    }
    @MessageMapping("stream")
    Flux<Message> stream(Message request) {
        log.info("Received stream request: {}", request);
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(index -> new Message(SERVER, STREAM, index))
                .log();
    }

    @ConnectMapping("shell-client")
    void connectShellClientAndAskForTelemetry (RSocketRequester requester, @Payload String client) {
        requester.rsocket()
                .onClose() // (1)
                .doFirst(() -> {
                    log.info("Client: {} CONNECTED.", client);
                    CLIENTS.add(requester); // (2)
                })
                .doOnError(error -> {
                    log.warn("Channel to client {} CLOSED", client); // (3)
                })
                .doFinally(consumer -> {
                    CLIENTS.remove(requester);
                    log.info("Client {} DISCONNECTED", client); // (4)
                })
                .subscribe();

        requester.route("client-status")
                .data("OPEN")
                .retrieveFlux(String.class)
                .doOnNext(s -> log.info("Client: {} Free Memory: {}.",client,s))
                .subscribe();
    }
}

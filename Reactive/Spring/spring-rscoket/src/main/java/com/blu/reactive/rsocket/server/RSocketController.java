package com.blu.reactive.rsocket.server;

import com.blu.reactive.rsocket.server.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class RSocketController {
    private static final String SERVER = "Server";
    private static final String RESPONSE = "Response";

    @MessageMapping("request-response")
    Message requestResponse(Message request) {
       log.info("Received request-response request: {}", request);
       return new Message(SERVER, RESPONSE);
    }
}

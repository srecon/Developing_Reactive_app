package com.blu.springwebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MsgController {
    private static final Logger logger = LoggerFactory.getLogger(MsgController.class);
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting (Message msg) throws Exception{
        logger.info("Got messge by channel /app/hello!: "+ msg.toString());
        Thread.sleep(1000); //Sleep for 1 second.
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(msg.getName()) + "!");
    }
}

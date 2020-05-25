package com.blu.std;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@RestController
@Configuration
@PropertySource("classpath:app.properties")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Value( "${spring.hello-world.quotes}" )
    private String suffix_quote;

    @RequestMapping("/")
    public String index() {
        return "Spring Boot Hello-world application for demo!";
    }

    @RequestMapping("/getQuote")
    public String getQuote() {
        LOGGER.info("/getQuote method invoked!");

        final String[] todaysQuote = new String[]{
                "Today you are you!",
                "Today was good.",
                "Today is the only day.",
                "What is not started today is never finished tomorrow."
        };
        final int rnd = new Random().nextInt(todaysQuote.length);

        return suffix_quote + " : " + todaysQuote[rnd];
    }

}

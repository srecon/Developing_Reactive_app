package com.blu.std;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@RestController
public class DemoController {
    @RequestMapping("/")
    public String index() {
        return "Spring Boot application for demo!";
    }
    @RequestMapping("/getQuote")
    public String getQuote() {
        final String[] todaysQuote = new String[]{"Today you are you!","Today was good.", "Today is the only day.", "What is not started today is never finished tomorrow."};
        final int rnd = new Random().nextInt(todaysQuote.length);
        return todaysQuote[rnd];
    }

}

package com.blu.std.quarkus;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/quotes")
public class TodaysQuote {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodaysQuote.class);

    @ConfigProperty(name = "suffix_quote")
    private String suffix_quote;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todays")
    public String getQuote(){
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
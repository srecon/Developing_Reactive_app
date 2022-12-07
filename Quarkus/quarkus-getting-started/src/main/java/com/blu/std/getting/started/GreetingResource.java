package com.blu.std.getting.started;



import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @ConfigProperty(name = "greeting")
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/byname/{name}")
    public String helloByName(@PathParam("name") String arg){
        return "Hello " + arg + ". " + getGreeting();
    }
}
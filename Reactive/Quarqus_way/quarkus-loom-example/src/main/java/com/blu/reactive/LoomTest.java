package com.blu.reactive;

import io.smallrye.common.annotation.RunOnVirtualThread;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@Path("/loom")
public class LoomTest {
    @GET
    @Path("/virtual")
    @RunOnVirtualThread
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        System.out.println("Testing Project Loom!");
        // invoke somewhereinblog 1 milliontimes in virtual threads
        for(int i = 0; i < 100 ; i++){
            //Thread.startVirtualThread(() -> getURL("https://www.somewhereinblog.net/")).start();
            String url = getURL("https://www.somewhereinblog.net/");
        }
        return "Hello from RESTEasy Reactive";
    }
    static String getURL(String url) {
        try (InputStream in = new URL(url).openStream()) {
            byte[] bytes = in.readAllBytes(); // ALERT, ALERT!
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.blu.std.quarkus;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

/**
 * REST CALL curl -w "\n" http://localhost:8080/tweet/todayss
 * */

@Path("/tweet")
public class Tweets {

    private static final Logger logger = LoggerFactory.getLogger(Tweets.class);
    private static final String CACHE_NAME= "thin-cache";

    @Inject
    IgniteConfiguration ignite;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/todays")
    public String hello() {
        logger.info("/tweet/todays method invokes");
        String ret = "hello";
        if(ignite.getIgniteClient().isPresent()){

            ClientCache<String, String> clientCache = ignite.getIgniteClient().get().getOrCreateCache(CACHE_NAME);
            // put a few value
            clientCache.put("Moscow", "095");
            clientCache.put("Vladimir", "033");
            // get the region code of the Vladimir
            String val = clientCache.get("Vladimir");

            logger.info("Print value: {}", val);

            return ret;

        }
        return "Exception occurs!!";
    }

}
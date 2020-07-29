package com.blu.std.quarkus;


import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * REST CALL curl -w "\n" http://localhost:8080/tweet/byprofile/{name}
 * */

@Path("/tweet")
public class Tweets {

    private static final Logger logger = LoggerFactory.getLogger(Tweets.class);
    private static final String CACHE_NAME= "SQL_PUBLIC_PERSON";

    @Inject
    IgniteConfiguration ignite;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/byprofile/{nick}")
    public String getTweetByProfile(@PathParam("nick") String nickname) {
        logger.info("/byprofile/{nick} API invoked, method arg:" + nickname);

        if(ignite.getIgniteClient().isPresent()){

            ClientCache<Long, String> clientCache = ignite.getIgniteClient().get().cache(CACHE_NAME);

            String qry = "SELECT p.name, t.tweet\n" +
                    "FROM Person p, Tweets t\n" +
                    "WHERE t.person_id = p.id " +
                    "and p.nick = ?";


            List<List<?>> rows = clientCache.query(new SqlFieldsQuery(qry).setArgs(nickname)).getAll();


            return rows.get(0).get(1).toString();

        } else {
            return "Exception occurs!!";
        }
    }

}
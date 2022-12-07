package com.blu.reactive;
import io.smallrye.common.annotation.NonBlocking;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/*
* NonBlocking REST API
* **/

@Path("/way")
public class ReactiveWay {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @NonBlocking
    public String getWay()
    {
        return "First Reactive REST!! IO Thread:" + Thread.currentThread().getName();
    }
}

package com.blu.services;

import com.blu.dto.Person;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class HttpEndpoint {

    @Channel("upload")
    MutinyEmitter<Person> emitter;

    /**
    * curl -X POST -H "Content-Type: application/json" -d '{"name":"Shamim", "age":45}' http://localhost:8080/
    * */

    @POST
    public Uni<Response> upload(Person person){
        return emitter.send(person)
                .replaceWith(Response.accepted().build())
                .onFailure()
                .recoverWithItem(t ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(t.getMessage()).build());

    }

    @GET
    @Path("/getall")
    public Uni<List<Person>> getAll(){
        return Person.listAll();
    }
}

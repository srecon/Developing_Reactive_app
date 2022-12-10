package com.blu.reactive;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.mutiny.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@Path("/uni")
public class ReactiveByUni {

    @Inject
    Vertx vertx;

    @Inject
    BookService service;

    @Inject
    Market market;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @NonBlocking
    public Uni<String> uniCal(){

        return vertx.fileSystem().readFile("sometxt.txt")
                .onItem().transform(buffer -> buffer.toString())
                .ifNoItem().after(Duration.ofSeconds(1)).fail()
                .onFailure().recoverWithItem("Opps!!");

    }
    @GET
    @Path("/404")
    //@Produces(MediaType.TEXT_PLAIN)
    // NonBlocking by default
    public Uni<Response> uniResponse(){
        return vertx.fileSystem().readFile("sometxt.txt")
                    .onItem().transform(buffer -> buffer.toString())
                    .onItem().transform(content ->
                        Response.ok(content).build())
                    .onFailure().recoverWithItem(
                        Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/multi")
    @Produces(MediaType.TEXT_PLAIN)
    // NonBlocking by default
    public Multi<String> multiResponse(){
        return vertx.fileSystem().open("war-and-peace.txt", new OpenOptions().setRead(true))
                .onItem().transformToMulti(asyncFile -> asyncFile.toMulti())
                .onItem().transform(b -> b.toString());
    }

    @GET
    @Path("/multibooks")
    @Produces(MediaType.APPLICATION_JSON)
    // NonBlocking by default
    public Multi<Book> multiBooks(){
        Multi<Long> ticks = Multi.createFrom().ticks().every(Duration.ofSeconds(1));
        Multi<Book> books = service.getBooks();


        return Multi.createBy().combining().streams(ticks, books).asTuple()
                .onItem().transform(Tuple2::getItem2);
    }


    @ApplicationScoped
    private static class BookService{
        private final List<Book> books = List.of(
            new Book(0,"HighPerformance Inmemory computing with Apache Ignitw",List.of("Shamim","Misha","Timur")),
            new Book(1,"The Apache Ignite Book",List.of("Shamim","Misha"))

        );
        Multi<Book> getBooks(){
            return Multi.createFrom().iterable(books);
        }
    }

    @GET
    @Path("/market")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Quote> market() {
        return market.getEventStream();
    }

    // Helper classes goes here

    public static class Quote {
        public final String company;
        public final double value;

        public Quote(String company, double value) {
            this.company = company;
            this.value = value;
        }
    }
    @ApplicationScoped
    public static class Market {
        Multi<Quote> getEventStream() {
            return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                    .onItem().transform(x -> getRandomQuote());
        }

        Random random = new Random();

        private Quote getRandomQuote() {
            int i = random.nextInt(3);
            String company = "MacroHard";
            if (i ==0) {
                company = "Divinator";
            } else if (i == 1) {
                company = "Black Coat";
            }

            double value = random.nextInt(200) * random.nextDouble();

            return new Quote(company, value);
        }
    }
}

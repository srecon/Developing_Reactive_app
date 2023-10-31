package com.blu.protocols.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GraphQLClient {
    private static final Logger logger = LoggerFactory.getLogger(GraphQLClient.class);
    private static final String QlURL="http://localhost:8080/graphql";
    private static final String request = "{bookById(id: 2) {id}}";

    public static void main(String[] args) {
        logger.info("Calling GraphQL API!!");

        GraphQlClient qc = HttpGraphQlClient.create(WebClient.create(QlURL));
        Mono<Book> bookResponse =  qc.document(request).retrieve("id").toEntity(Book.class);
        System.out.println("Book:" + bookResponse.toString());
    }
}

package com.blu.reactive;


import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.reactive.client.adapter.AdaptedReactivePulsarClientFactory;
import org.apache.pulsar.reactive.client.api.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@QuarkusMain

public class ReactiveConsumer {
    private static final String CLUSTER_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "my-topic";
    public static void main(String[] args) {
        System.out.println("Reactive consumer!!");
        try{
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(CLUSTER_URL).build();
            //init a Reactive client
            ReactivePulsarClient reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(client);
            // consuming message
            ReactiveMessageReader<String> reader = reactivePulsarClient.messageReader(Schema.STRING)
                    .topic(TOPIC_NAME)
                    .build();
            reader.readMany()
                    .map(Message::getValue)
                    .subscribe(System.out::println);

        } catch(PulsarClientException ex){
            System.out.println("Pulsar Exception:" + ex.toString());
        }
    }
}

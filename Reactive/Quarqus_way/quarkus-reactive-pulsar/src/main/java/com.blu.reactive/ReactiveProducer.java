package com.blu.reactive;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.mutiny.Multi;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.reactive.client.adapter.AdaptedReactivePulsarClientFactory;
import org.apache.pulsar.reactive.client.api.MessageSpec;
import org.apache.pulsar.reactive.client.api.ReactiveMessageSender;
import org.apache.pulsar.reactive.client.api.ReactivePulsarClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@QuarkusMain

public class ReactiveProducer {

    private static final String CLUSTER_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "my-topic";
    public static void main(String[] args) {
        System.out.println("Pulsar reactive producer!");
        try{
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(CLUSTER_URL).build();
            //init a Reactive client
            ReactivePulsarClient reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(client);
            // sending message
            ReactiveMessageSender<String> messageSender = reactivePulsarClient
                    .messageSender(Schema.STRING)
                    .topic(TOPIC_NAME)
                    //.maxInflight(100) // set when cache is available
                    .build();
            Mono<MessageId> messageId = messageSender.sendOne(MessageSpec.of("Reactive Hello World!!"));
            //Flux<String> = messageSender.sendMany()
            //Multi.createFrom().ticks().every(2).onOverflow()
            messageId.subscribe(System.out::println);
            // close connections!!!

        } catch(PulsarClientException ex){
            System.out.println("Pulsar Exception:" + ex.toString());
        }

    }
}

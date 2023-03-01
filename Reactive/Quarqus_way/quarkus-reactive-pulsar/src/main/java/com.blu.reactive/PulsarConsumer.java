package com.blu.reactive;


import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.pulsar.client.api.*;

//@QuarkusMain
public class PulsarConsumer {
    private static final String CLUSTER_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "my-topic";
    public static void main(String[] args) {
        System.out.println("Pulsar consumer!!");

        try{
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(CLUSTER_URL).build();
            Consumer<String> consumer = client.newConsumer(Schema.STRING)
                    .topic(TOPIC_NAME).subscriptionName("first-subscription").subscribe();

            Message<String> msg = consumer.receive();
            String msgValue = msg.getValue();
            System.out.println("Got message:" + msgValue);

            // close connections
            consumer.close();
            client.close();
        } catch(PulsarClientException ex){
            System.out.println("Pulsar client exception:" + ex.getMessage());
        }

    }
}

package com.blu.reactive;

import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

//@QuarkusMain
public class PulsarProducer {

    /**
     * Pulsar connection
     * 1. pulsar://localhost:6650
     * 2. http://localhost:8080
     * */
    private static final String CLUSTER_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "my-topic";
    public static void main(String[] args) {
        System.out.println("Pulsar producer..");
        // init a pulsar connection
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(CLUSTER_URL).build();
            // init a producer
            Producer<String> producer = client.newProducer(Schema.STRING)
                    .topic(TOPIC_NAME).create();
            // send a test message
            producer.send("Hello World!!");
            System.out.println("Producer sent a message!!");
            // close all the connections
            //producer.close();
            producer.closeAsync()
                    .thenRun(() -> System.out.println("Producer closed"))
                    .exceptionally((ex) -> {
                        System.err.println("Failed to close producer: " + ex);
                        return null;
                    });
            client.close();
        } catch (PulsarClientException ex) {
            System.out.println("Pulsar exception:" + ex.getMessage());
        }

    }
}

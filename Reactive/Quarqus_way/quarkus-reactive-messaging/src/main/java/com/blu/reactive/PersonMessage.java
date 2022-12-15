package com.blu.reactive;

import org.eclipse.microprofile.reactive.messaging.Message;

public class PersonMessage implements Message<String> {

    private final String payload;

    public PersonMessage(String payload) {
        this.payload = payload;
    }

    public PersonMessage(long l) {
        this(Long.toString(l));
    }

    @Override
    public String getPayload() {
        return payload;
    }

}

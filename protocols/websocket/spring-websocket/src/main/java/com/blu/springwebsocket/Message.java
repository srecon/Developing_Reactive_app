package com.blu.springwebsocket;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class Message {

    private String name;

    public Message() {
    }

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                '}';
    }
}

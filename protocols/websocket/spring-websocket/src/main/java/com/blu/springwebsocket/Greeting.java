package com.blu.springwebsocket;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class Greeting {
    @NonNull
    private String content;

}

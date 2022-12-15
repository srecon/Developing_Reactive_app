package com.blu.reactive;

import javax.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class ReactiveMessaging {

    @Outgoing("longTicks")
    public Multi<Long> longTicks() {
        return Multi.createFrom().items(1L, 2L, 3L);
    }
    @Incoming("longTicks")
    public void print(Long ticks) {
        System.out.println("Print:" + ticks);
    }
    @Incoming("person-chanel")
    public void print(String msg){
        System.out.println("Message got, channel person:" + msg);
    }
}

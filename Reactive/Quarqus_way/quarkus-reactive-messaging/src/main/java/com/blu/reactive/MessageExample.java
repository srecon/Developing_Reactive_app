package com.blu.reactive;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.MutinyEmitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.Duration;

@ApplicationScoped
public class MessageExample {

    //@Channel("person-chanel")
    //private MutinyEmitter<PersonMessage> personEmitter;

//    @Outgoing("person-chanel")
//    @Broadcast
//    public Multi<PersonMessage> ticks() {
//        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
//                .onOverflow().drop()
//                .onItem().transform(PersonMessage::new);
//    }
//
//    // downstream for channel person-channel
//
//    @Incoming("person-chanel")
//    public void print(String msg){
//        System.out.println("Message got:" + msg);
//    }


}

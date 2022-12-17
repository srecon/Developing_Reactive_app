package com.blu.reactive;

import io.smallrye.mutiny.Multi;

import javax.inject.Singleton;
import java.time.Duration;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.smallrye.reactive.messaging.annotations.Broadcast;

@Singleton
public class TickGenerator {
    @Outgoing("ticks")
    //@Broadcast
    Multi<Long> generateTicks(){
        return Multi.createFrom().ticks()
                .every(Duration.ofSeconds(2))
                .onOverflow().drop();
    }
}

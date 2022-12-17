package com.blu.reactive;

import javax.inject.Singleton;
import java.net.InetAddress;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
@Singleton
public class Processor {
    @Incoming("ticks")
    @Outgoing("processed")
    String process(Long payload) throws Exception{
        String val = String.valueOf(payload.longValue());
        System.out.println("Value consumed:"+ val);
        return val+= "consumed in pod (" + InetAddress.getLocalHost().getHostName() + ")";
    }
}

package com.blu.services;

import com.blu.dto.Person;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Incoming;


import io.quarkus.hibernate.reactive.panache.Panache;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Persisting {

    @Incoming("database")
    public Uni<Void> persist(Person person){

        return Panache.withTransaction(person::persist) .replaceWithVoid();

    }
}

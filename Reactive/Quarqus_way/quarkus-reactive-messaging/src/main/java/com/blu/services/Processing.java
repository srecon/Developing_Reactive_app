package com.blu.services;

import com.blu.dto.Person;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Processing {

    @Incoming("upload")
    @Outgoing("database")
    public Person validate(Person person){


        if(person != null && person.age <=0){
            System.out.println("Person age <0");
            throw new IllegalArgumentException("Person age <0");
        }
        // capialize the person name
        person.name = capString(person.name);
        return person;
    }

    private String capString(String name){

        return name.toUpperCase();
    }
}

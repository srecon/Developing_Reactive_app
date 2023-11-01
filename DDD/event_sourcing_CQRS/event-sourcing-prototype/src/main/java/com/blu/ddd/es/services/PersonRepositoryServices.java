package com.blu.ddd.es.services;

import com.blu.ddd.es.repository.PersonEventRepository;
import com.blu.ddd.es.events.Event;

public class PersonRepositoryServices {
    private PersonEventRepository eventRepository;

    public PersonRepositoryServices(PersonEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void createPerson(String userId, Event event) {
        eventRepository.storePersonEvent(userId, event);
    }
//    public void addContacts(String userId, Event){
//
//    }
}

package event.sourcing.es.services;

import event.sourcing.es.events.Event;
import event.sourcing.es.repository.PersonEventRepository;

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

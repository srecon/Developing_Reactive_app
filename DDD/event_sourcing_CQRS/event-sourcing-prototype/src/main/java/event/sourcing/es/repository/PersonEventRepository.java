package event.sourcing.es.repository;

import event.sourcing.es.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class PersonEventRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonEventRepository.class);

    private Map<String, List<Event> > eventStore = new HashMap<String, List<Event>>();

    public void storePersonEvent(String eventId, Event event) {
        logger.info("Persists person Event with Id..." + eventId);
        List<Event> events = eventStore.get(eventId);

        if(events == null || events.isEmpty() ){
            events = new ArrayList<Event>();
            events.add(event);
            eventStore.put(eventId, events);
        } else {
            events.add(event);
        }
        // print the Map
        for(Map.Entry mp: eventStore.entrySet()){
            logger.info("Id: "+ mp.getKey() + " Values: "+ mp.getValue());
        }
    }
}

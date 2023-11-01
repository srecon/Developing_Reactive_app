package com.blu.ddd.prototype;

import com.blu.ddd.es.events.PersonAddContactsEvent;
import com.blu.ddd.es.events.PersonCreatedEvent;
import com.blu.ddd.es.repository.PersonEventRepository;
import com.blu.ddd.es.services.PersonRepositoryServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventSourcingPrototypeApplication {
	private static final Logger logger = LoggerFactory.getLogger(EventSourcingPrototypeApplication.class);

	public static void main(String[] args) {

		logger.info("Starting the prototype....");
		SpringApplication.run(EventSourcingPrototypeApplication.class, args);
		logger.info("[Testing Events...]");

		PersonCreatedEvent person1 = new PersonCreatedEvent("1", "Shamim", "Bhuiyan");

		PersonAddContactsEvent contactPhone = new PersonAddContactsEvent("Phone", "9096860911");
		PersonAddContactsEvent contactEmail = new PersonAddContactsEvent("e-mail","srecon@ya.ru");

		PersonCreatedEvent person2 = new PersonCreatedEvent("2","Mishel", "Shamimvic");
		// Persist the created person
		PersonRepositoryServices personRepositoryServices = new PersonRepositoryServices(new PersonEventRepository());
		personRepositoryServices.createPerson("1", person1);

		personRepositoryServices.createPerson("1", contactEmail);
		personRepositoryServices.createPerson("1", contactPhone);

		personRepositoryServices.createPerson("2", person2);

	}

}

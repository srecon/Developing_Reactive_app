package com.blu.reactive.crac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@SpringBootApplication
public class SpringBootCRaCTest {
	private static final Logger LOG = LoggerFactory.getLogger(SpringBootCRaCTest.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCRaCTest.class, args);
	}

	@RestController
	class CustomersHttpController {

		@GetMapping("/customers")
		Collection<Customer> getAllCustomers() {
			LOG.info("Invoked method: {getAllCustomers} ");
			return Set.of(new Customer(1, "A"), new Customer(2, "B"), new Customer(3, "C"));
		}

		record Customer(Integer id, String name) {
		}

	}
}

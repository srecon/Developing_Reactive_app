package com.blu.junodb.javaexamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages={
		"com.blu.junodb.javaexamples", "com.paypal.juno"})
@ImportResource("classpath:spring-client.xml")
@EnableAutoConfiguration
public class JavaExamplesApplication implements CommandLineRunner {

    public JavaExamplesApplication(DAO daoService) {
        DAOService = daoService;
    }

    public static void main(String[] args) {

		SpringApplication.run(JavaExamplesApplication.class, args);
	}
	@Autowired
	private DAO DAOService;

	@Override
	public void run(String... args) throws Exception {
		// add an entry without ttl
		DAOService.addEnrty("Key1", "value1");
		// get the entry
		String val = DAOService.getEntryByKey("Key1");
		System.out.println("Value-> " + val);

	}
}

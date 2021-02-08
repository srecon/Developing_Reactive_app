package com.blu.std;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * java -jar -Dserver.port=8080 ./target/springboot-hello-world-1.0-SNAPSHOT.jar
 * */
@SpringBootApplication
public class App 
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        LOGGER.info("Spring Boot Hello-World app runs!");
        SpringApplication.run(App.class);
    }

}

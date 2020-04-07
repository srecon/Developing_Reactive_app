package com.blu.std;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Spring boot MicroService
 * Provides user profiles
 * Run: java -jar -Dserver.port=8282 ./target/springboot-profile-service-1.0-SNAPSHOT.jar
 */

@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Spring boot <Profile> MicroService runs!" );
        SpringApplication.run(App.class);
    }
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

}

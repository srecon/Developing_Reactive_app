package com.blu.std;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring boot MicroService
 * Provides user profiles
 * run: java -jar -Dserver.port=8181 ./target/springboot-product-service-1.0-SNAPSHOT.jar
 */

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring boot <Product> MicroService runs!" );
        SpringApplication.run(App.class);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
}

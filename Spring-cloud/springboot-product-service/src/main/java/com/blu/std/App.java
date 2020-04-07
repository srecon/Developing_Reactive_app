package com.blu.std;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring boot MicroService
 * Provides user profiles
 * run: java -jar -Dserver.port=8181 ./target/springboot-product-service-1.0-SNAPSHOT.jar
 */

@SpringBootApplication
@EnableDiscoveryClient
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        LOGGER.info("Spring boot <Product> MicroService runs!");
        SpringApplication.run(App.class);
    }

}

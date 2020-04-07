package com.blu.std;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***
 * http://localhost:8761/
 */

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaAppServer {
    public static void main(String[] args) {
        System.out.println("Eureka server started!!");
        SpringApplication.run(SpringCloudEurekaAppServer.class, args);

    }
}

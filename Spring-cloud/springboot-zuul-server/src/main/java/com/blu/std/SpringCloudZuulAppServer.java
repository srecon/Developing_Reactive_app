package com.blu.std;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class SpringCloudZuulAppServer {
    public static void main(String[] args) {
        System.out.println("Netflix Zuul server is up and running!");
        SpringApplication.run(SpringCloudZuulAppServer.class, args);
    }
}

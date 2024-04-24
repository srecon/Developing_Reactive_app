package com.blu.grpc.grpcclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(GrpcClientApplication.class, args);
		GrettingClient client = applicationContext.getBean(GrettingClient.class);
		client.sayHello("MacOs->", "Hello GRPC");
	}

}

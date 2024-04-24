package com.blu.grpc.grpcserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcServerApplication {
	private static final Logger LOG = LoggerFactory.getLogger(GrpcServerApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(GrpcServerApplication.class, args);

	}

}

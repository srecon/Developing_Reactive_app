package com.blu.grpc.grpcclient;

import com.imertyildiz.grpcproto.HelloWorldRequest;
import com.imertyildiz.grpcproto.HelloWorldResponse;
import com.imertyildiz.grpcproto.HelloWorldServiceGrpc;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GrettingClient {
    private static final Logger LOG = LoggerFactory.getLogger(GrettingClient.class);
    @GrpcClient("grpc-server")
    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceStub;

    public void sayHello(String sender, String message){
        HelloWorldRequest helloWorldRequest = HelloWorldRequest.newBuilder().setClientName(sender)
                .setRequestMessage(message).build();
        HelloWorldResponse helloWorldResponse = this.helloWorldServiceStub.helloWorld(helloWorldRequest);
        LOG.info(String.format("Server sent a response: %1s", helloWorldResponse.getResponseMessage()));
    }

}

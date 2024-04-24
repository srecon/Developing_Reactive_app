package com.blu.grpc.grpcserver;

import com.imertyildiz.grpcproto.HelloWorldRequest;
import com.imertyildiz.grpcproto.HelloWorldResponse;
import com.imertyildiz.grpcproto.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class Greeting extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {
    private static final Logger LOG = LoggerFactory.getLogger(Greeting.class);

    @Override
    public void helloWorld(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {
        //super.helloWorld(request, responseObserver);
        LOG.info("Got message from client!: " + request.getClientName() + " Message: " + request.getRequestMessage());
        HelloWorldResponse response = HelloWorldResponse.newBuilder().setResponseMessage("Hi: "+ request.getClientName()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

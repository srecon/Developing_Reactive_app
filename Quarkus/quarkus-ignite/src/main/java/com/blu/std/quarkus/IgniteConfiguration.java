package com.blu.std.quarkus;

import io.quarkus.runtime.Startup;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.Optional;

@ApplicationScoped
public class IgniteConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(IgniteConfiguration.class);

    @ConfigProperty(name = "host")
    String HOST;
    @ConfigProperty(name = "port")
    String PORT ;

    @Produces
    public Optional<IgniteClient> getIgniteClient(){

        ClientConfiguration cfg = new ClientConfiguration().setAddresses(getHOST()+":"+getPORT());

        LOGGER.info("Ignite client configured to host:" + getHOST() + " at port:" + getPORT());

        Optional<IgniteClient> igniteClient = Optional.empty();

        try{
            igniteClient = Optional.ofNullable(Ignition.startClient(cfg));

        } catch(ClientException e) {
            e.printStackTrace();
        }

        return igniteClient;
    }

    public String getHOST() {
        return HOST;
    }

    public String getPORT() {
        return PORT;
    }
}

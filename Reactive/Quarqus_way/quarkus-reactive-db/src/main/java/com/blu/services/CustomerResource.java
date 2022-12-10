package com.blu.services;


import com.blu.dto.Customer;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("customer")
public class CustomerResource {

    @GET
    public Multi<Customer> findAll(){
        return Customer.streamAll(Sort.by("name"));
    }
    @GET
    @Path("{id}")
    public Uni<Customer> findById(@RestPath Long id){

        return Customer.<Customer>findById(id)
                .onItem().ifNull().failWith(
                        new WebApplicationException("Failed to find customer",
                                Response.Status.NOT_FOUND)
                );
    }
}

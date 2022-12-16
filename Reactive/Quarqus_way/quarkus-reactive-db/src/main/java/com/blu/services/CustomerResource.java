package com.blu.services;


import com.blu.dto.Customer;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    /**
     * example POST request through CURL: curl -X POST -H "Content-Type: application/json" -d '{"name": "shamim"}' http://localhost:8080/customer
     **/

    @POST
    public Uni<Response> createCustomer(@Valid Customer customer){
        System.out.println("Customer:" + customer.name);

        if(customer.id != null){
            throw new WebApplicationException("Invalid customer set on request, Customer ID should be null", 422);
        }
        return Panache.withTransaction(customer::persist)
                .replaceWith(Response.ok(customer).status(Response.Status.CREATED).build());
    }
}

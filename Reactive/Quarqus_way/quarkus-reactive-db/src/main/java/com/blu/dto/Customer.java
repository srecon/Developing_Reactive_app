package com.blu.dto;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends PanacheEntity {

    @Column(nullable = false)
    @NotBlank(message = "Customer name can not be blank")
    @Length(min = 3, message = "Customer names must be at least three characters")
    public String name;

    //public List<Order> orders = new ArrayList<>();


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}

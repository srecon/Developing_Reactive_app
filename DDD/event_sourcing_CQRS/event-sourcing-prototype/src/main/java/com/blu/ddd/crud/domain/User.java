package com.blu.ddd.crud.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;


@Getter @Setter @NoArgsConstructor
public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private Set<Address> addresses;
    private Set<Contact> contacts;
}

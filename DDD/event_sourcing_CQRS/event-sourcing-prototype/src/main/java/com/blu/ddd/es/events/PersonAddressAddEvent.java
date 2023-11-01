package com.blu.ddd.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonAddressAddEvent extends Event {
    private String city;
    private String state;
    private String postCode;
}

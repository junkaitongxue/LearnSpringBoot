package com.dreamkite.pg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Person {
    // 对应数据的ip_p
    String idP;
    String lastName;
    String firstName;
    String address;
    String city;

    public Person(String firstName, String lastName, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }
}

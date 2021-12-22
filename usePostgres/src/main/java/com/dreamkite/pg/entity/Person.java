package com.dreamkite.pg.entity;

import lombok.Data;

@Data
public class Person {
    // 对应数据的ip_p
    String idP;
    String lastName;
    String firstName;
    String address;
    String city;
}

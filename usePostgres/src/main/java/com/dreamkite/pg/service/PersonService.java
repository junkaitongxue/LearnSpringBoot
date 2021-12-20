package com.dreamkite.pg.service;

import com.dreamkite.pg.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public String hi() {
        return "hello";
    }

    public String bye() {
        return "bye";
    }

    public void insert(String id, String lastName, String firstName, String address, String city) {
        personDao.insertPerson(id, lastName, firstName, address, city);
    }
}

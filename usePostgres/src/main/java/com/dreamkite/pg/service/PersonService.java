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

}

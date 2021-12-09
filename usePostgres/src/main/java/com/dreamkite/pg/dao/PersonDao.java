package com.dreamkite.pg.dao;


import com.dreamkite.pg.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao {

    Person queryPersonById(String id);
    

    void insertPerson(String id, String lastName, String firstName, String address, String city);

}

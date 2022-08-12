package com.dreamkite.pg.dao;


import com.dreamkite.pg.entity.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao {

    Person queryPersonById(String id);

    String queryFirstNameById(String id);

    void insertPerson(@Param("id") int id, @Param("lastName") String lastName, @Param("firstName") String firstName,
                      @Param("address") String address, @Param("city") String city);

    void insertUpdatePerson(Person person);

    void insertPersonWithoutId(@Param("lastName") String lastName, @Param("firstName") String firstName,
                               @Param("address") String address, @Param("city") String city);

    void batchInsertPerson(@Param("personList") List<Person> personList);

    void deletePerson(String id);
}

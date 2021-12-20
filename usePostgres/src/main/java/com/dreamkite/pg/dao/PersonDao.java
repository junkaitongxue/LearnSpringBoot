package com.dreamkite.pg.dao;


import com.dreamkite.pg.entity.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao {

    Person queryPersonById(String id);

    String queryFirstNameById(String id);

    void insertPerson(@Param("id") String id, @Param("lastName") String lastName, @Param("firstName") String firstName,
                      @Param("address") String address, @Param("city") String city);

}

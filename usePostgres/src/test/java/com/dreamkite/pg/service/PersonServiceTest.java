package com.dreamkite.pg.service;

import com.dreamkite.pg.MockPgDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class PersonServiceTest extends MockPgDao {
    @Autowired
    PersonService personService;

    @Test
    void delete() {
        System.out.println(personService.queryPersonById("1"));
        personService.delete("1");
        System.out.println(personService.queryPersonById("1"));
        personService.insert("1", "h", "jk", "china", "dongguan");
        System.out.println(personService.queryPersonById("1"));
    }
}
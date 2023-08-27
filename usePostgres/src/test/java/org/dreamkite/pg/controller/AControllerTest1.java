package org.dreamkite.pg.controller;

import org.dreamkite.pg.UsePgApplication;
import org.dreamkite.pg.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 测试spy
 */
@ActiveProfiles({"test"})
@SpringBootTest(classes = {UsePgApplication.class})
@ExtendWith(SpringExtension.class)
@ExtendWith({MockitoExtension.class})
class AControllerTest1 {

    @Autowired
    @InjectMocks
    private AController aController;

    @Spy
    private PersonService personService;

    @Test
    void hi() {
        Mockito.doReturn("hjk").when(personService).hi();

        assertEquals("hjk", aController.hi());
    }

    @Test
    void bye() {
        assertEquals("bye", aController.bye());
    }
}
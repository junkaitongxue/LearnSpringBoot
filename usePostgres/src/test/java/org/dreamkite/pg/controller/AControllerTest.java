package org.dreamkite.pg.controller;

import org.dreamkite.pg.UsePgApplication;
import org.dreamkite.pg.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试mock
 */
@ActiveProfiles({"test"})
@SpringBootTest(classes = {UsePgApplication.class})
@ExtendWith(SpringExtension.class)
@ExtendWith({MockitoExtension.class})
class AControllerTest {

    @Autowired
    @InjectMocks
    private AController aController;

    @Mock
    private PersonService personService;

    @Test
    void hi() {
        Mockito.doReturn("hjk").when(personService).hi();

        assertEquals("hjk", aController.hi());
    }

    @Test
    void bye() {
        assertNull(aController.bye());
    }
}
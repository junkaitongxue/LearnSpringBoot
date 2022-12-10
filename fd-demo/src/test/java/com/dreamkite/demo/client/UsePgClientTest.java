package com.dreamkite.demo.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsePgClientTest {

    @Autowired
    UsePgClient usePgClient;

    @Test
    void getPersonById() {
        usePgClient.getPersonById("1");
    }
}
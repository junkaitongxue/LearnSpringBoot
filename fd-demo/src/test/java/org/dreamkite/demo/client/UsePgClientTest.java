package org.dreamkite.demo.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsePgClientTest {

    @Autowired
    UsePgClient usePgClient;

    @Test
    void getPersonById() {
        usePgClient.getPersonById("1");
    }
}
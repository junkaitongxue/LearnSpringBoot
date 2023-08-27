package org.dreamkite.demo.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolTest {

    @Autowired
    School school;

    @Test
    void doWork() {
        school.doWork();
    }
}
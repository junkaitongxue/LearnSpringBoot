package com.dreamkite.demo.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchoolTest {

    @Autowired
    School school;

    @Test
    void doWork() {
        school.doWork();
    }
}
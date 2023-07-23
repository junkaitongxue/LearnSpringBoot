package com.dreamkite.db.mongo.service;

import com.dreamkite.db.mongo.service.InsertService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class InsertServiceTest {
    @Resource
    InsertService insertService;

    @Test
    void insertOne() {
        insertService.insertOne();
    }

    @Test
    void insertMany() {
    }
}
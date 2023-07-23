package com.dreamkite.db.mongo.service;

import com.dreamkite.db.mongo.service.QueryCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class QueryCollectionServiceTest {

    @Resource
    QueryCollectionService queryCollectionService;

    @Test
    void getCollectionNames() {
        log.info(queryCollectionService.getCollectionNames().toString());
    }

    @Test
    void collectionExists() {
    }
}
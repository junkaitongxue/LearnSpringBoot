package com.dreamkite.db.service.mongo;

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
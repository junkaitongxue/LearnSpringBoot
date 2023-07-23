package com.dreamkite.db.mongo.service;

import com.dreamkite.db.mongo.service.RemoveCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class RemoveCollectionServiceTest {

    @Resource
    RemoveCollectionService removeCollectionService;

    @Test
    void dropCollection() {
       log.info(removeCollectionService.dropCollection().toString());
    }
}
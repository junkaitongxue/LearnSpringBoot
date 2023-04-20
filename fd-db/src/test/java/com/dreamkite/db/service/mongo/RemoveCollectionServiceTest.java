package com.dreamkite.db.service.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

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
package com.dreamkite.db.service.mongo;

import com.dreamkite.db.service.mongo.CreateCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class CreateCollectionServiceTest {

    @Resource
    CreateCollectionService useMongoService;

    @Test
    void createCollection() {
        log.info(useMongoService.createCollection().toString());
    }

    @Test
    void createCollectionFixedSize() {
    }

    @Test
    void createCollectionValidation() {
    }
}
package com.dreamkite.db.mongo.service;

import com.dreamkite.db.mongo.service.SaveService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class SaveServiceTest {

    @Resource
    SaveService saveService;

    @Test
    void save() {
        saveService.save();
    }
}
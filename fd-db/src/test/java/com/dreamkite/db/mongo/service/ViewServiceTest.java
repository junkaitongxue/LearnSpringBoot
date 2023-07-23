package com.dreamkite.db.mongo.service;

import com.dreamkite.db.mongo.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class ViewServiceTest {

    @Resource
    ViewService viewService;

    @Test
    void createView() {
        log.info(viewService.createView().toString());
    }

    @Test
    void dropView() {
    }
}
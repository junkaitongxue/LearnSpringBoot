package com.dreamkite.db.service.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RemoveServiceTest {
    @Resource
    RemoveService removeService;

    @Test
    void remove() {
        removeService.remove();
    }

    @Test
    void findAndRemove() {
    }

    @Test
    void findAllAndRemove() {
    }
}
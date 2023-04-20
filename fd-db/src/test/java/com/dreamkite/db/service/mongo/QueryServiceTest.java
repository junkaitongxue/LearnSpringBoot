package com.dreamkite.db.service.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class QueryServiceTest {

    @Resource
    QueryService queryService;

    @Test
    void findAll() {
        queryService.findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findByCondition() {
    }

    @Test
    void findByConditionAndSort() {
    }

    @Test
    void findByConditionAndSortLimit() {
    }

    @Test
    void findByConditionAndSortSkip() {
    }

    @Test
    void findByExistsField() {
    }

    @Test
    void findByAndCondition() {
    }
}
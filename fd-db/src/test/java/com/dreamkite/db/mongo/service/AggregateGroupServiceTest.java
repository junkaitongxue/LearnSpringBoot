package com.dreamkite.db.mongo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class AggregateGroupServiceTest {
    @Resource
    AggregateGroupService aggregateGroupService;

    @Test
    void aggregationGroupCount() {
        aggregateGroupService.aggregationGroupCount();
    }

    @Test
    void aggregationGroupMax() {
    }

    @Test
    void aggregationGroupMin() {
    }

    @Test
    void aggregationGroupSum() {
    }

    @Test
    void aggregationGroupAvg() {
    }

    @Test
    void aggregationGroupFirst() {
    }

    @Test
    void aggregationGroupLast() {
    }

    @Test
    void aggregationGroupPush() {
    }
}
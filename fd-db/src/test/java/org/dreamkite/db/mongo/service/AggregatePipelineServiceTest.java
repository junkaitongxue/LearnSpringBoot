package org.dreamkite.db.mongo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class AggregatePipelineServiceTest {
    @Resource
    AggregatePipelineService aggregatePipelineService;

    @Test
    void aggregateGroupMatch() {
        aggregatePipelineService.aggregateGroupMatch();
    }

    @Test
    void aggregateGroupSort() {
    }

    @Test
    void aggregateGroupLimit() {
    }

    @Test
    void aggregateGroupSkip() {
    }

    @Test
    void aggregateGroupProject() {
    }

    @Test
    void aggregateProjectUnwind() {
    }
}
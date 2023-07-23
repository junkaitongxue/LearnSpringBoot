package com.dreamkite.db.mongo.service;

import com.dreamkite.db.mongo.service.AggregateLookUpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class AggregateLookUpServiceTest {
    @Resource
    AggregateLookUpService aggregateLookUpService;

    @Test
    void lookupForeignEmpty() {
        aggregateLookUpService.lookupForeignEmpty();
    }

    @Test
    void lookupForeignWithForeignJudge() {
        aggregateLookUpService.lookupForeignWithForeignJudge();
    }

    @Test
    void lookupForeignWithForeignJudgeOneToOne() {
        aggregateLookUpService.lookupForeignWithForeignJudgeOneToOne();
    }


    @Test
    void lookupForeignWithForeignJudgeOneToOne1() {
        aggregateLookUpService.lookupForeignWithForeignJudgeOneToOne1();
    }

}
package com.dreamkite.demo.service.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FixStrategyContextTest {
    @Resource
    FixStrategyContext fixStrategyContext;

    @Test
    void getCorrespondingIFixStrategy() {
        System.out.println(fixStrategyContext.getCorrespondingIFixStrategy("dbFix"));
        System.out.println(fixStrategyContext.getCorrespondingIFixStrategy("sysFix"));
    }

    @Test
    void getCorrespondingFixStrategy() {
        System.out.println(fixStrategyContext.getCorrespondingFixStrategy("dbFix"));
        System.out.println(fixStrategyContext.getCorrespondingFixStrategy("sysFix"));
    }
}
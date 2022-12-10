package com.dreamkite.demo.service;

import com.dreamkite.demo.context.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SingleCaseServiceTest {

    @Autowired
    @Lazy
    SingleCaseService singleCaseService;

    @Test
    void addTwoStr() {
        log.info("start to say Hi");
        singleCaseService.sayHi();
//        SpringContextHolder.getBean(SingleCaseService.class).sayHi();
    }
}
package com.dreamkite.demo.context;

import com.dreamkite.demo.controller.AController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SpringContextHolderTest {

    @Test
    void getBean() {
        AController ac = SpringContextHolder.getBean(AController.class);
        log.info(ac.hi());
    }
}
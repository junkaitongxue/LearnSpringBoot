package com.dreamkite.db.service.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UpdateServiceTest {

    @Resource
    UpdateService updateService;

    @Test
    void update() {
        // 没有更新插入的新的如何查看到： db.users.find({"_id": ObjectId("XX")})
        updateService.update();
    }

    @Test
    void updateFirst() {
    }

    @Test
    void updateMany() {
    }
}
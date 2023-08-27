package org.dreamkite.pg.controller;

import org.dreamkite.pg.UsePgApplication;
import org.dreamkite.pg.feign.CommonFeign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试spy， 使用ReflectionTestUtils
 */
@ActiveProfiles({"test"})
@SpringBootTest(classes = {UsePgApplication.class})
@ExtendWith(SpringExtension.class)
@ExtendWith({MockitoExtension.class})
class AControllerTest4 {

    @Autowired
    private AController aController;

    @Autowired
    private CommonFeign commonFeign;

    @Test
    void hi() {
        CommonFeign spy = Mockito.spy(commonFeign);
        Mockito.doReturn("hjk").when(spy).test();
        ReflectionTestUtils.setField(aController, "commonFeign", spy);

        assertEquals("hjk", aController.test());
    }
}
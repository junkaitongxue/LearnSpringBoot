package com.dreamkite.pg.controller;

import com.dreamkite.pg.UsePgApplication;
import com.dreamkite.pg.dao.PersonDao;
import com.dreamkite.pg.feign.CommonFeign;
import com.dreamkite.pg.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试spyBean
 */
@ActiveProfiles({"test"})
@SpringBootTest(classes = {UsePgApplication.class})
@ExtendWith(SpringExtension.class)
@ExtendWith({MockitoExtension.class})
class AControllerTest3 {

    @Autowired
    private AController aController;

    @SpyBean
    private CommonFeign commonFeign;

    @Test
    void hi() {
        // 不会真实执行commonFeign.test()
        Mockito.doReturn("hjk").when(commonFeign).test();
        // 会真实执行commonFeign.test()
        // Mockito.when(commonFeign.test()).thenReturn("hjk");
        assertEquals("hjk", aController.test());
    }
}
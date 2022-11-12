package com.dreamkite.utils.common;

import com.dreamkite.utils.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
@ExtendWith({MockitoExtension.class})
@ActiveProfiles({"ut"})
class SpringUtilsTest {

    @Test
    void getBean() {
        MockedStatic<SpringUtils> springUtilsMockedStatic = Mockito.mockStatic(SpringUtils.class);
        springUtilsMockedStatic.when(() -> SpringUtils.getBean("someBeanName")).thenReturn("injectedBean");

        Assertions.assertEquals("injectedBean", SpringUtils.getBean("someBeanName"));
    }
}
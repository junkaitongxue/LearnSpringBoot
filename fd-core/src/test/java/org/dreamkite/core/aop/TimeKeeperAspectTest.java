package org.dreamkite.core.aop;

import org.dreamkite.core.test.CommonSpringTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;


class TimeKeeperAspectTest extends CommonSpringTest {

    @Resource
    AComponent aComponent;

    @Test
    void test1() {
        aComponent.test1();
    }

    @Test
    void test2() {
        aComponent.test2();
    }
}
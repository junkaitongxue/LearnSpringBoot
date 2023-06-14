package com.dreamkite.core.aop;

import org.springframework.stereotype.Component;

@Component
@TimeKeeper
public class AComponent {
    void test1() {
        System.out.println("1");
    }

    @TimeKeeper
    void test2() {
        System.out.println("2");
    }

}

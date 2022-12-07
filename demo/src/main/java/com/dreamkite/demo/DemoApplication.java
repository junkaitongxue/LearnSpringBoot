package com.dreamkite.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

// @ComponentScan({"com.dreamkite.demo.controller", "com.dreamkite.demo.filter"...})
// 不使用@ComponentScan去定义的话默认则是使用当前启动类的父路径：({"com.dreamkite.demo"})
//@ServletComponentScan("com.dreamkite.demo.filter")
@ComponentScan("com.dreamkite")
@EnableScheduling
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

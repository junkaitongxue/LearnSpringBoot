package com.dreamkite.demo;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @ComponentScan({"com.dreamkite.demo.controller", "com.dreamkite.demo.filter"...})
// 不使用@ComponentScan去定义的话默认则是使用当前启动类的父路径：({"com.dreamkite.demo"})
@ServletComponentScan("com.dreamkite.demo.filter")
@EnableSwagger2
@EnableKnife4j
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

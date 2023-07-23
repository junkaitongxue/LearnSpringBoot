package com.dreamkite.demo;

import com.dreamkite.db.filter.MongoFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

// @ComponentScan({"com.dreamkite.demo.controller", "com.dreamkite.demo.filter"...})
// 不使用@ComponentScan去定义的话默认则是使用当前启动类的父路径：({"com.dreamkite.demo"})
//@ServletComponentScan("com.dreamkite.demo.filter")
@EnableScheduling
// 禁用自动配置mongo实例
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan(basePackages = "com.dreamkite", excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MongoFilter.class))
//@ComponentScan(basePackages = "com.dreamkite", excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.dreamkite.db.mongo.*")})
@MapperScan(value = "com.dreamkite.demo.repo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

package com.dreamkite.pg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.dreamkite.pg.dao")
public class UsePgApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsePgApplication.class, args);
    }

}

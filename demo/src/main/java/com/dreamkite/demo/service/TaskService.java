package com.dreamkite.demo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TaskService {
    // 如何生效Async?
    // 1、如果需要使用@Async达到异步执行的效果，需要在入口类上面声明!否则配置了不生效
    // 2、
    @Async
    @SneakyThrows
    public void longTask() {
        log.info("start to execute long task...");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end to execute long task...");
    }

    @Async("asyncTaskExecutor")
    @SneakyThrows
    public void longTaskWithSpecialThreadPool() {
        log.info("start to execute long task...");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end to execute long task...");
    }
}

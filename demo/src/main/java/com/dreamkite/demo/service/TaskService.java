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
    // 1、如果需要使用@Async达到异步执行的效果，需要在入口类上面声明@EnableAsync!否则配置了不生效
    // 2、新建一个类，用@Service等进行注解，对需要异步的函数添加@Async，如需要执行执行的进程池则可使用
    // 注意：异步的情况不要带返回值；
    @Async
    @SneakyThrows
    public void longTask() {
        log.info("start to execute long task...");
        TimeUnit.SECONDS.sleep(10);
        log.info("end to execute long task...");
    }

    @Async("asyncTaskExecutor")
    @SneakyThrows
    public void longTaskWithSpecialThreadPool() {
        log.info("start to execute long task...");
        TimeUnit.SECONDS.sleep(10);

        log.info("end to execute long task...");
    }

    /**
     * 注意异步的情况不建议使用返回值，因为调用方拿到的值会是null
     */
    @Async("asyncTaskExecutor")
    @SneakyThrows
    public String longTaskWithRet() {
        log.info("start to execute long task WithRet...");
        TimeUnit.SECONDS.sleep(10);
        log.info("end to execute long task WithRet...");
        return "Success";
    }
}

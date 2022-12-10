package com.dreamkite.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的配置
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    private static final int MAX_POOL_SIZE = 50;

    private static final int CORE_POOL_SIZE = 20;
    private static final int KEEP_ALIVE_SECONDS = 120;
    private static final int AWAIT_SECONDS = 600;

    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool-");
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }

    @Bean("asyncTaskExecutor1")
    public TaskExecutor asyncTaskExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 设置线程容量
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        // 设置默认线程名称
        executor.setThreadNamePrefix("a-thread-pool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 任务的最长等待否则进行终止的等待时间
        executor.setAwaitTerminationSeconds(AWAIT_SECONDS);
        return executor;
    }
}
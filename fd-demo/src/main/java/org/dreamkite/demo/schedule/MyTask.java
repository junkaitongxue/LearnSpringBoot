package org.dreamkite.demo.schedule;

import org.dreamkite.demo.service.TaskService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 如何配置定时任务
 * 1、需要添加@EnableScheduling注解到入口类声明上面才能生效！！
 * 2、@Component注释类，这里我使用@RestController，因为任务同时也是接口
 * 3、使用
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class MyTask {
    @Autowired
    private TaskService taskService;

    // 固定时间段执行,  从上次调用结束到下一次调用之间的固定时间（以毫秒为单位）
    // http://localhost:8080/task/starTask
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    @GetMapping("/starTask")
    public String starTask() {
        log.info("start to start task...");
        // 以下直接调用本类中longTask的方式是没法生效的！！需要调用taskService.longTask()才能生效
        // longTask();

        taskService.longTask();
        log.info("end to start task...");
        return "starTask successfully.";
    }

    // 以下的方式定义，在starTask中调用longTask是没法生效的！！
    @Async("asyncTaskExecutor")
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

    // 固定时间点执行, 该参数为cron表达式，从左到右: [秒] [分] [小时] [日] [月] [周]
    // 在线生成对应的表达式 ==》 https://tool.lu/crontab/
    @Scheduled(cron = "0 3 0 4 * ?")
    public void starTask1() {
        log.info("start to start task2...");
        String ret = taskService.longTaskWithRet();

        log.info("execute task1...");
    }


    // 两次调用之间固定的毫秒数,  从上次开始调用到下一次开始调用之间的固定时间（以毫秒为单位）
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void starTask2() {
        log.info("start to start task2...");
        taskService.longTaskWithSpecialThreadPool();
        log.info("end to start task2...");
    }


    // 两次调用之间固定的毫秒数,  从上次开始调用到下一次开始调用之间的固定时间（以毫秒为单位）
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void starTask3() {
        log.info("start to start task3...");
        String ret = taskService.longTaskWithRet();
        log.info("end to start task3... " + ret);
    }
}

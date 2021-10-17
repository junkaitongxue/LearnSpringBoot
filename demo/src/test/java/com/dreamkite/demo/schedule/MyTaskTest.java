package com.dreamkite.demo.schedule;

import com.dreamkite.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes={DemoApplication.class})
@ExtendWith(SpringExtension.class)
class MyTaskTest {
    @Autowired
    MyTask myTask;

    @Test
    void starTask() {
        myTask.starTask();
        System.out.println("hjk");
    }
}
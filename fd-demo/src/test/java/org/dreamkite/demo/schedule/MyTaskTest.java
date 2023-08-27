package org.dreamkite.demo.schedule;

import org.dreamkite.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes={DemoApplication.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
class MyTaskTest {
    @Autowired
    MyTask myTask;

    @Test
    void starTask() {
        myTask.starTask();
    }
}
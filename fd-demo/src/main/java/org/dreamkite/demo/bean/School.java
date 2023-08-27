package org.dreamkite.demo.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class School {

    private final Teacher teacher;

    public void doWork(){
        teacher.teach();
    }
}

package org.dreamkite.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Slf4j
public class SingleCaseService {

    public SingleCaseService() {
      log.info("start to init SingleCaseService");
    }

    public String sayHi() {
        return "hi";
    }
}

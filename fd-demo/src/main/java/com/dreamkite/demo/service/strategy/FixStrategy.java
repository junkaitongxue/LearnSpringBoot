package com.dreamkite.demo.service.strategy;

import com.dreamkite.demo.service.strategy.entity.FixInfo;
import org.apache.commons.lang.NotImplementedException;

public abstract class FixStrategy {

    boolean isSupport(FixInfo fixInfo) {
        throw new NotImplementedException();
    }

    void fix(FixInfo fixInfo) {
        throw new NotImplementedException();
    }

    void commonHandle() {

    }

}

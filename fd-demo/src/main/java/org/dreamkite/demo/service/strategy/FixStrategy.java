package org.dreamkite.demo.service.strategy;

import org.dreamkite.demo.service.strategy.entity.FixInfo;
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

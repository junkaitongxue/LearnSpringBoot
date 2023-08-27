package org.dreamkite.demo.service.strategy;

import org.dreamkite.demo.service.strategy.entity.FixInfo;

public interface IFixStrategy {

    boolean isSupport(FixInfo fixInfo);

    void fix(FixInfo fixInfo);
}

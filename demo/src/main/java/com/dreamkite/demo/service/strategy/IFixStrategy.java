package com.dreamkite.demo.service.strategy;

import com.dreamkite.demo.service.strategy.entity.FixInfo;

public interface IFixStrategy {

    boolean isSupport(FixInfo fixInfo);

    void fix(FixInfo fixInfo);
}

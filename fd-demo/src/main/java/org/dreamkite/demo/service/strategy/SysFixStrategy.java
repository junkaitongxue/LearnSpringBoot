package org.dreamkite.demo.service.strategy;

import org.dreamkite.demo.service.strategy.entity.FixInfo;
import org.springframework.stereotype.Component;

@Component("sysFix")
public class SysFixStrategy extends FixStrategy implements IFixStrategy {

    @Override
    public boolean isSupport(FixInfo fixInfo) {
        return false;
    }

    @Override
    public void fix(FixInfo fixInfo) {
        //
    }
}

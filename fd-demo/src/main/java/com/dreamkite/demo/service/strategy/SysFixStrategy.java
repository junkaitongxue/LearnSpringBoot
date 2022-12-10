package com.dreamkite.demo.service.strategy;

import com.dreamkite.demo.service.strategy.entity.FixInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

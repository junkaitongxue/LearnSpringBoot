package com.dreamkite.demo.service.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class FixStrategyContext {
    @Resource
    Map<String, IFixStrategy> iFixStrategyMap;

    @Resource
    Map<String, FixStrategy> fixStrategyMap;

    public IFixStrategy getCorrespondingIFixStrategy(String type) {
        return iFixStrategyMap.get(type);
    }

    public FixStrategy getCorrespondingFixStrategy(String type) {
        return fixStrategyMap.get(type);
    }
}

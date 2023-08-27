package org.dreamkite.pg.feign;


import org.springframework.stereotype.Component;

/**
 * CommonFeign还没打通，临时手动继承实现
 */
@Component
public class CommonFeignImpl implements CommonFeign {
    @Override
    public String test() {
        return null;
    }
}

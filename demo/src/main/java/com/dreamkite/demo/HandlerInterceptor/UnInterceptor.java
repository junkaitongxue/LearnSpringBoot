package com.dreamkite.demo.HandlerInterceptor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnInterceptor {
}

package com.dreamkite.core.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class TimeKeeperAspect {
    @Around("@within(com.dreamkite.core.aop.TimeKeeper) && !@annotation(com.dreamkite.core.aop.TimeKeeper)")
    public Object keepForClass(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return keepForMethod(proceedingJoinPoint);
    }

    @Around("@annotation(com.dreamkite.core.aop.TimeKeeper)")
    private Object keepForMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        log.info("Start to run {}::{}", signature.getDeclaringTypeName(), signature.getName());
        LocalDateTime start = LocalDateTime.now();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            log.info("End to run {}:: {}, spent {}ms", signature.getDeclaringTypeName(), signature.getName(),
                    Duration.between(start, LocalDateTime.now()).toMillis());
        }
    }
}

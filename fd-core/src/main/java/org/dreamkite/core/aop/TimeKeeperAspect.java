package org.dreamkite.core.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 启动该计时器需要添加配置：timeKeeper.enable=true
 */
@Slf4j
@Component
@Aspect
@ConditionalOnProperty(name = "timeKeeper.enable")
public class TimeKeeperAspect {
    @Around("@within(org.dreamkite.core.aop.TimeKeeper) && !@annotation(org.dreamkite.core.aop.TimeKeeper)")
    public Object keepForClass(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return keepForMethod(proceedingJoinPoint);
    }

    @Around("@annotation(org.dreamkite.core.aop.TimeKeeper)")
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

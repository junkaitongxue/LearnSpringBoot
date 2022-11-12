package com.dreamkite.utils.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器会检测容器中的所有Bean，如果发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
 * 自动调用该Bean的setApplicationContextAware()方法，调用该方法时，会将容器本身作为参数传给该方法——该方法中的实现部分将
 * Spring传入的参数（容器本身）赋给该类对象的applicationContext实例变量
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}

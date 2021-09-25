package com.dreamkite.demo.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第三种springboot使用Filter的方法
 * 通过 Java 代码显式配置 Filter ，功能强大，配置灵活。只需要把每个自定义的 Filter 声明成 Bean 交给 Spring 管理即可，
 * 还可以设置匹配的 URL 、指定 Filter 的先后顺序。
 *
 */
@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean registerMyFilter(){
        FilterRegistrationBean<MyFilter00> bean = new FilterRegistrationBean<>();
        bean.setOrder(1);
        bean.setFilter(new MyFilter00());
        // 匹配"/hello/"下面的所有url
        bean.addUrlPatterns("/hello/*");
        return bean;
    }

    @Bean
    public FilterRegistrationBean registerMyAnotherFilter(){
        FilterRegistrationBean<MyFilter01> bean = new FilterRegistrationBean<>();
        bean.setOrder(2);
        bean.setFilter(new MyFilter01());
        // 匹配所有url
        bean.addUrlPatterns("/*");
        return bean;
    }
}
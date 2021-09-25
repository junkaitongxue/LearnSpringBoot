package com.dreamkite.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 配置过滤器给spring管理的方法一：@Component + @Order
 * 该注解方式配置简单，支持自定义 Filter 顺序。缺点是只能拦截所有URL，不能通过配置去拦截指定的 URL。
 */
@Component
@Order(1) // 当有多个Filter时，这里的@Order(1)注解会指定执行顺序，数字越小，越优先执行，如果只写@Order，默认顺序值是Integer.MAX_VALUE。
@Slf4j
public class MyFilter00 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter00");
        // 要继续处理请求，必须添加 filterChain.doFilter()
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
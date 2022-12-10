package com.dreamkite.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 配置过滤器给spring管理的方法二：@WebFilter+@ServletComponentScan
 * 注意： 需要在启动类上增加@ServletComponentScan("com.dreamkite.demo.filter")注解
 * 该注解方式支持对 Filter 匹配指定URL，但是不支持指定 Filter 的执行顺序。
 */
@WebFilter(urlPatterns = "/filter/*")
@Slf4j
public class MyFilter01 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter01");
        // 要继续处理请求，必须添加 filterChain.doFilter()
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
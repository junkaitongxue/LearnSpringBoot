package com.dreamkite.demo.HandlerInterceptor.controller;

import com.dreamkite.demo.HandlerInterceptor.UnInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    /**
     * http://localhost:8080/interceptor/test
     * @return result
     */
    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    /**
     * http://localhost:8080/interceptor/test1
     * @return result
     */
    @UnInterceptor
    @GetMapping("/test1")
    @ResponseBody
    public String test1() {
        return "不被拦截";
    }
}
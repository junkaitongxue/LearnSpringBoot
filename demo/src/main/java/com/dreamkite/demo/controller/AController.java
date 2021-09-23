package com.dreamkite.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {


    /**
     * 浏览器输入：http://localhost:8080/hi
     *
     * @return hi
     */
    @GetMapping("/hi")
    public String hi() {
        return "hi, springboot.";
    }

}

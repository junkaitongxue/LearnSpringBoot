package com.dreamkite.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {

    @GetMapping("/hi")
    public String hi() {
        return "hi, springboot.";
    }

}

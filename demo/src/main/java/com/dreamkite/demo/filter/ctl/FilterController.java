package com.dreamkite.demo.filter.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {


    /**
     * 浏览器输入：http://localhost:8080/filter/test
     *
     * @return hi
     */
    @GetMapping("/filter/test")
    public String hi() {
        return "hi, filter.";
    }

}

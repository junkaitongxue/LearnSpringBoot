package com.dreamkite.pg.controller;

import com.dreamkite.pg.service.PaperService;
import com.dreamkite.pg.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PaperService paperService;


    /**
     * 浏览器输入：http://localhost:8080/hi
     *
     * @return hi
     */
    @GetMapping("/hi")
    public String hi() {
        return personService.hi();
    }


}

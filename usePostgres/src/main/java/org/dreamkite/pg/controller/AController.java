package org.dreamkite.pg.controller;

import org.dreamkite.pg.entity.Person;
import org.dreamkite.pg.feign.CommonFeign;
import org.dreamkite.pg.service.PaperService;
import org.dreamkite.pg.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PaperService paperService;

    @Autowired(required = false)
    private CommonFeign commonFeign;

    /**
     * 浏览器输入：http://localhost:8080/hi
     *
     * @return hi
     */
    @GetMapping("/hi")
    public String hi() {
        return personService.hi();
    }

    /**
     * 浏览器输入：http://localhost:8080/hi
     *
     * @return hi
     */
    @GetMapping("/bye")
    public String bye() {
        return personService.bye();
    }

    /**
     * 浏览器输入：http://localhost:8080/test
     *
     * @return test
     */
    @GetMapping("/test")
    public String test() {
        return commonFeign.test();
    }

    /**
     * @return result
     */
    @PostMapping("/person/insert")
    public ResponseEntity personInsert(@RequestParam("id") String id, @RequestParam("lastName") String lastName,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("address") String address, @RequestParam("city") String city) {
        personService.insert(id, lastName, firstName, address, city);
        return ResponseEntity.ok().build();
    }


    /**
     * @return result
     */
    @GetMapping("/person/get/{id}")
    public ResponseEntity personSelectById(@PathVariable("id") String id) {
        Person person = personService.queryPersonById(id);
        return ResponseEntity.ok().body(person);
    }

}

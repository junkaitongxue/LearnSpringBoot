package org.dreamkite.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsePgClient {
    @Autowired
    private RestTemplate restTemplate;

    private final static String baseUrl = "http://localhost:8081/";

    public void getPersonById(String id) {
        String url = baseUrl + "person/get/" + id;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(forEntity.getBody());
    }

}

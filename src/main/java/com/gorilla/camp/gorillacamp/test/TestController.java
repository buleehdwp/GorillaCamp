package com.gorilla.camp.gorillacamp.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/webMappingTest")
    public String webMappingTest(){

        return "하하";
    }
}

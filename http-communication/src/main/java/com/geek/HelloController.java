package com.geek;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/https")
    public String https() {
        return "https";
    }

    @GetMapping("/http")
    public String http() {
        return "http";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}

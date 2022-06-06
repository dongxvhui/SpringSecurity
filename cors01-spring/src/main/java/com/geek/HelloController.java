package com.geek;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @CrossOrigin(origins = "http://localhost:8081")
//    @PostMapping("/post")
//    public String post(){
//        return "hello post";
//    }

    @GetMapping("/post")
    public String post(){
        return "hello post";
    }
}

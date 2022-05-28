package com.geek.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @PostMapping("/withdraw")
    public void withdraw(){
        System.out.println("执行了一次转账操作");
    }
}

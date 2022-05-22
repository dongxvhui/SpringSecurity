package com.geek.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "login success";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring security";
    }


}

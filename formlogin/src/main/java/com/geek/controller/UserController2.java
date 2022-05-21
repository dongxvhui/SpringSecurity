package com.geek.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController2 {
    @RequestMapping("/authentication")
    public void authentication(Authentication authentication){
        System.out.println("authentication = " + authentication);
    }

    @RequestMapping("/principal")
    public void pricipal(Principal principal){
        System.out.println("principal = " + principal);
    }
}

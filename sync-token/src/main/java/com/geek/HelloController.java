package com.geek;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @PostMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello csrf!";
    }

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }
}

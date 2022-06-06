package com.geek;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() throws Exception {
//        throw new ServletException("aaa");
        return "hello";
//        throw new AuthenticationServiceException("bbb");
    }
}

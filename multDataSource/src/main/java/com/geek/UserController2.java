package com.geek;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/info")
    public void info(HttpServletRequest request){
        String remoteUser  = request.getRemoteUser();
        Authentication authentication = ((Authentication) request.getUserPrincipal());
        boolean admin = request.isUserInRole("admin");
        System.out.println("remoteUser = " + remoteUser);
        System.out.println("auth.getName () = " + authentication.getName());
        System.out.println("admin = " + admin);
    }
}

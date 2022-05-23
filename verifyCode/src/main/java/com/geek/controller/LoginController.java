package com.geek.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    Producer producer;
    @GetMapping("vc.jpg")
    public void getVerifyCode(HttpServletResponse response, HttpSession session)throws IOException{
        response.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("kaptcha", text);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = response.getOutputStream()){
            ImageIO.write(image, "jpg", out);
        }
    }

    @RequestMapping("/index")
    public String index(){
        return "login success";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}

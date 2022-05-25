package com.geek;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.geek.mapper")
public class Passwordencoder02Application {

    public static void main(String[] args) {
        SpringApplication.run(Passwordencoder02Application.class, args);
    }

}

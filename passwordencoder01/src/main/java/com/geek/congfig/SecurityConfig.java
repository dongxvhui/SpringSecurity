package com.geek.congfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean  //提供PasswordEncoder实例
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("javaboy")
                .password("{bcrypt}$2a$10$.n2u8N/n0ghnywA26mhIXefXzTUKFZNvTQCAhGEeiggOMRvQhWfBG") //在test中生成加密字符串
                .roles("admin")
                .and()
                .withUser("geek")
                .password("{noop}1234")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}

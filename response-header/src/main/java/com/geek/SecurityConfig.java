package com.geek;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hello.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .headers()
                .cacheControl().disable()//请求经过Spring Security过滤器，同时开启缓存功能（不配置Cache-Control、Pragma和Expires这三个缓存相关的响应头）。
                .contentTypeOptions().disable() //移除X-Content-Type-Options响应头。
                .frameOptions().deny() //该页面不允许在frame页面中展示

                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .headers()
//                .xssProtection().block(false) //启用XSS过滤。
                .contentSecurityPolicy("default-src 'self';script-src 'self';" +
                        " object-src 'none'; style-src cdn.javaboy.org; img-src *; child-src https:");


    }

}




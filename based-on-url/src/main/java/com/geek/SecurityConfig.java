package com.geek;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("geek")
                .password("{noop}123")
                .roles("ADMIN")
                .and()
                .withUser("大魔王")
                .password("{noop}123")
                .roles("USER")
                .and()
                .withUser("小魔王")
                .password("{noop}123")
                .roles("READ_INFO");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").access("hasAnyRole('USER','ADMIN')")
                .antMatchers("/getinfo").hasAuthority("READ_INFO")
                .anyRequest().access("isAuthenticated()")
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}

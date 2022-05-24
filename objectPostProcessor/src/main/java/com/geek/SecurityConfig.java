package com.geek;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .withObjectPostProcessor(new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {

                    @Override
                    public <O extends UsernamePasswordAuthenticationFilter> O postProcess(O object) {
                        object.setUsernameParameter("name");
                        object.setPasswordParameter("passwd");
                        object.setAuthenticationSuccessHandler((request, response, authentication) -> {
                            response.getWriter().write("login success");
                        });
                        return object;
                    }
                })
                .and()
                .csrf().disable();
    }
}
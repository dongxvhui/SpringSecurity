package com.geek;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig{

    @Bean
    UserDetailsService us(){
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User.withUsername("javaboy").password("{noop}123").roles("admin").build());

        return users;
    }

    @Configuration
    @Order(1)
    static class SecurityConfig01 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http)throws Exception{
            InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
            users.createUser(User.withUsername("bar").password("{noop}123").roles("admin").build());
            http.antMatcher("/bar/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/bar/login")
                    .successHandler((request, response, authentication) -> {
                        response.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(authentication);
                        response.getWriter().write(s);
                    })
                    .permitAll()
                    .and()
                    .csrf().disable()
                    .userDetailsService(users);
        }
    }

    @Configuration
    @Order(2)
    static class SecurityConfig02 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.inMemoryAuthentication().withUser("javagirl").password("{noop}123").roles("admin");
        }
        @Override
        protected void configure(HttpSecurity http)throws Exception{
            InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
            users.createUser(User.withUsername("foo").password("{noop}123").roles("admin").build());
            http.antMatcher("/foo/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/foo/login")
                    .successHandler((request, response, authentication) -> {
                        response.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(authentication);
                        response.getWriter().write(s);
                    })
                    .permitAll()
                    .and()
                    .csrf().disable()
                    .userDetailsService(users);
        }
    }

}

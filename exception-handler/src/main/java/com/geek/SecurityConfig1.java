package com.geek;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        AntPathRequestMatcher matcher1 = new AntPathRequestMatcher("/qq/**");
        AntPathRequestMatcher matcher2 = new AntPathRequestMatcher("/wx/**");
        http.authorizeRequests()
                .antMatchers("/wx/**").hasRole("wx")
                .antMatchers("/qq/**").hasRole("qq")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor((request, response, authenticationException) -> {
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("请登录，QQ用户");
                },matcher1)
                .defaultAuthenticationEntryPointFor((request, response, authenticationException) -> {
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("请登录，WX用户");
                },matcher2)
                .defaultAccessDeniedHandlerFor((request, response, accessDeniedException) -> {
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write("权限不足，QQ用户");
                },matcher1)
                .defaultAccessDeniedHandlerFor((request, response, accessDeniedException) -> {
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write("权限不足，WX用户");
                },matcher2)
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}




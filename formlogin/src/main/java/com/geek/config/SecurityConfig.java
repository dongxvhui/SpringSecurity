package com.geek.config;

import com.geek.handle.MyAuthenticationFailureHandler;
import com.geek.handle.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login.html")
                .loginPage("/mylogin.html")
                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/index")   //实现登录成功后的跳转，会自动重定向到登录之前的地址
//                .successForwardUrl("/index")    //实现登录成功后的跳转，跳转到successForwardUrl所指定的页面
//                .successHandler(new MyAuthenticationSuccessHandler())
                .defaultSuccessUrl("/index.html")
                .failureHandler(new MyAuthenticationFailureHandler())
//                .failureUrl("/login.html")
//                .failureForwardUrl("/mylogin.html")
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .logout()
//                .logoutUrl("/logout")
                .logoutRequestMatcher(new OrRequestMatcher(
                        new AntPathRequestMatcher("logout1","GET"),
                        new AntPathRequestMatcher("logout2","POST")
                ))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/mylogin.html")
                .and()
                .csrf().disable();
    }
//   自定义SavedRequestAwareAuthenticationSuccessHandler
//    SavedRequestAwareAuthenticationSuccessHandler successHandler(){
//        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
//        handler.setDefaultTargetUrl("/index");
//        handler.setTargetUrlParameter("target");
//        return handler;
//    }

}

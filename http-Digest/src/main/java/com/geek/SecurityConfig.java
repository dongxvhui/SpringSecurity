package com.geek;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(digestAuthenticationEntryPoint())
                .and()
                .addFilter(digestAuthenticationFilter());
    }

    DigestAuthenticationEntryPoint digestAuthenticationEntryPoint(){
        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
        entryPoint.setNonceValiditySeconds(3600);
        entryPoint.setRealmName("myrealm");
        entryPoint.setKey("geek");
        return entryPoint;
    }

    DigestAuthenticationFilter digestAuthenticationFilter() throws Exception{
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());
        filter.setUserDetailsService(userDetailsServiceBean());
        filter.setPasswordAlreadyEncoded(true);
        return filter;
    }
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception{
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("geek").password("eb540eb79743f9bf7ecc021549319fe0").roles("admin").build());
        //输入的密码是原密码123，而不是这段摘要
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

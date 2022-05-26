package com.geek.config;

import com.geek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    //修改strength参数，没有变，原因不知  //加上会报错
    @Bean
    PasswordEncoder passwordEncoder(){
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder(18));
        encoders.put("ldap",new LdapShaPasswordEncoder());
        encoders.put("MD4",new Md4PasswordEncoder());
        encoders.put("MD5",new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2",new Pbkdf2PasswordEncoder());
        encoders.put("scrypt",new SCryptPasswordEncoder());
        encoders.put("SHA-1",new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256",new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256",new StandardPasswordEncoder());
        encoders.put("argon2",new Argon2PasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
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

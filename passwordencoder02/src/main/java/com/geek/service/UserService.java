package com.geek.service;

import com.geek.mapper.UserMapper;
import com.geek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        Integer result = userMapper.updatePassword(user.getUsername(), newPassword);
        if(result == 1){
            ((User) user).setPassword(newPassword);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.loadUserByUsername(username);
    }
}

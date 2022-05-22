package com.geek.mapper;

import com.geek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUsersByUsername(String username);
}

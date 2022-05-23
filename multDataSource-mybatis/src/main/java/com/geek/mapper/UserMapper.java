package com.geek.mapper;

import com.geek.model.Role;
import com.geek.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Role> getRolesByUid(Integer id);
    User loadUserByUsername(String username);
}

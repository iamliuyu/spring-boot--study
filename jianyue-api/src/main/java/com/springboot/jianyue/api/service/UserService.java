package com.springboot.jianyue.api.service;

import com.springboot.jianyue.api.entity.User;
import com.springboot.jianyue.api.entity.dto.UserDTO;

import java.util.List;

public interface UserService {
    //根据手机号获取用户信息
    User getUserByMobile(String mobile);

    //登录方法
    int signIn(UserDTO userDTO);

    //根据用户id获取用户
    User getUserById(int userId);

    //修改用户信息
    void updateUser(User user);

    //用户注册
    void signUp(UserDTO userDTO);

    int insertUsers(List<User> users);
}

package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.User;
import com.springboot.jianyue.api.entity.dto.UserDTO;
import com.springboot.jianyue.api.service.UserService;
import com.springboot.jianyue.api.util.StatusConst;
import com.springboot.jianyue.api.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private UserService userService;
//    @Test
//    public void getUserByMobile() {
//        User user1 = userService.getUserByMobile("111");
//        User user2 = userService.getUserByMobile("1234");
//        List<User> userList = new ArrayList<User>();
//        userList.add(user1);
//        userList.add(user2);
//        System.out.println(userList);
//        System.out.println(userService.insertUsers(userList));
//    }

//    @Test
//    public void signIn() {
//        UserDTO loginUser = new UserDTO();
//        loginUser.setMobile("13988889999");
//        String base64Pass = StringUtil.getBase64Encoder("111");
//        loginUser.setPassword(base64Pass);
//        int status = userService.signIn(loginUser);
//        assertEquals(StatusConst.SUCCESS, status);
//    }
}
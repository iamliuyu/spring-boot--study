package com.springboot.mybatis.service.impl;

import com.springboot.mybatis.entity.SysUser;
import com.springboot.mybatis.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {
    @Resource
    private SysUserService sysUserService;

    @Test
    public void selectAll() {
    }

    @Test
    public void getOne() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void insert() {
        SysUser sysUser = new SysUser();
        sysUser.setMobile("121");
        sysUser.setUsername("zhangsan");
        sysUser.setPassword("!2121");
        sysUser.setAvatar("dada.jpg");
        sysUserService.insert(sysUser);
    }

    @Test
    public void update() {
    }
}
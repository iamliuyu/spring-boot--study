package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Img;
import com.springboot.jianyue.api.service.ImgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImgServiceImplTest {
    @Resource
    private ImgService imgService;
    @Test
    public void selectImgsByAId() {
        List<Img> img =  imgService.selectImgsByAId(1);
        System.out.println(img);
    }
}
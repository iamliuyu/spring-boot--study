package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Img;
import com.springboot.jianyue.api.mapper.ImgMapper;
import com.springboot.jianyue.api.service.ImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {
    @Resource
    private ImgMapper imgMapper;

    @Override
    public List<Img> selectImgsByAId(int aId) {
        return imgMapper.selectImgsByAId(aId);
    }

    @Override
    public void insertImg(Img img) {
        imgMapper.insertImg(img);
    }
}

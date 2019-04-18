package com.springboot.jianyue.api.service;

import com.springboot.jianyue.api.entity.Img;

import java.util.List;

public interface ImgService {
    List<Img> selectImgsByAId(int aId);

    void insertImg(Img img);
}

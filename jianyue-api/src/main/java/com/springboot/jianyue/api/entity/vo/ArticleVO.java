package com.springboot.jianyue.api.entity.vo;

import com.springboot.jianyue.api.entity.Img;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVO {
    private Integer id;
    private Integer uId;
    private String title;
    private String content;
    private String createTime;
    private String nickname;
    private String avatar;
    private List<Img> imgs;
    private Integer commentCount;
}

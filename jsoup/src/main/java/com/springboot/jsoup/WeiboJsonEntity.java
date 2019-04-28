package com.springboot.jsoup;

import lombok.Data;

import java.util.List;

// 微博json对于的实体类
@Data
public class WeiboJsonEntity {
    private String pid;
    private List<String> js;
    private List<String> css;
    private String html;
    // get set
}
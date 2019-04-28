package com.springboot.jsoup;

import lombok.Data;

@Data
public class WeiboHotEntity {
    private Integer id = 0;
    private Integer sort = 0;
    private Integer num = 0;
    private String title;
    private String linkUrl;
    private String channel;
    private String date;
    // get set
}
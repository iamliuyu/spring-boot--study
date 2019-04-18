package com.springboot.jianyue.api.entity;

import lombok.Data;

@Data
public class Follow {
    private Integer id;
    private Integer fromUId;
    private Integer toUId;
}

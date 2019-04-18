package com.springboot.jianyue.api.entity.vo;

import lombok.Data;

@Data
public class FollowVO {
    private Integer toUId;
    private Integer fromUId;
    private String nickname;
    private String avatar;
    private String status;
}

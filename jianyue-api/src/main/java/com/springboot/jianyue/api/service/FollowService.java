package com.springboot.jianyue.api.service;

import com.springboot.jianyue.api.entity.Follow;
import com.springboot.jianyue.api.entity.vo.FollowVO;

import java.util.List;

public interface FollowService {
    Follow getFollow(int fromUId, int toUId);

    List<FollowVO> getFollowsByUId(int userId, int fromUId);

    List<FollowVO> getFansByUId(int userId, int toUId);

    void insertFollow(Follow follow);

    void deleteFollow(int fromUId, int toUId);
}

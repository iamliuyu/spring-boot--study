package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Follow;
import com.springboot.jianyue.api.entity.vo.FollowVO;
import com.springboot.jianyue.api.mapper.FollowMapper;
import com.springboot.jianyue.api.service.FollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Resource
    private FollowMapper followMapper;

    @Override
    public Follow getFollow(int fromUId, int toUId) {
        Follow follow = followMapper.getFollow(fromUId, toUId);
        return follow;
    }

    @Override
    public List<FollowVO> getFollowsByUId(int userId, int fromUId) {
        List<FollowVO> followVOList = followMapper.getFollowsByUId(fromUId);
        for (FollowVO followVO:followVOList) {
            if (followMapper.getFollow(followVO.getToUId(), userId) != null && followMapper.getFollow(userId, followVO.getToUId()) != null) {
                followVO.setStatus("互相关注");
            } else if (followMapper.getFollow(userId, followVO.getToUId()) != null) {
                followVO.setStatus("已关注");
            } else if (followVO.getToUId() == userId) {
                followVO.setStatus("自己");
            } else {
                followVO.setStatus("关注");
            }
        }
        return followVOList;
    }

    @Override
    public List<FollowVO> getFansByUId(int userId, int toUId) {
        List<FollowVO> followVOList = followMapper.getFansByUId(toUId);
        for (FollowVO followVO:followVOList) {
            if (followMapper.getFollow(followVO.getFromUId(), userId) != null && followMapper.getFollow(userId, followVO.getFromUId()) != null) {
                followVO.setStatus("互相关注");
            } else if (followMapper.getFollow(userId, followVO.getFromUId()) != null) {
                followVO.setStatus("已关注");
            } else if (followVO.getFromUId() == userId) {
                followVO.setStatus("自己");
            } else {
                followVO.setStatus("关注");
            }
        }
        return followVOList;
    }

    @Override
    public void insertFollow(Follow follow) {
        followMapper.insertFollow(follow);
    }

    @Override
    public void deleteFollow(int fromUId, int toUId) {
        followMapper.deleteFollow(fromUId, toUId);
    }
}

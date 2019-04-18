package com.springboot.jianyue.api.controller;

import com.springboot.jianyue.api.entity.Follow;
import com.springboot.jianyue.api.entity.vo.FollowVO;
import com.springboot.jianyue.api.service.FollowService;
import com.springboot.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/follow")
public class FollowController {
    @Resource
    private FollowService followService;

    @PostMapping("/add")
    public ResponseResult followUser(@RequestParam("fromUId") int fromUId, @RequestParam("toUId") int toUId) {
        Follow follow = new Follow();
        follow.setFromUId(fromUId);
        follow.setToUId(toUId);
        followService.insertFollow(follow);
        return ResponseResult.success();
    }

    @PostMapping("/cancel")
    public ResponseResult cancelFollow(@RequestParam("fromUId") int fromUId, @RequestParam("toUId") int toUId) {
        followService.deleteFollow(fromUId, toUId);
        return ResponseResult.success();
    }

    @GetMapping("/followList")
    public ResponseResult getFollowList(@RequestParam("userId") int userId, @RequestParam("fromUId") int fromUId) {
        List<FollowVO> followVOList = followService.getFollowsByUId(userId, fromUId);
        return ResponseResult.success(followVOList);
    }

    @GetMapping("/fansList")
    public ResponseResult getFansList(@RequestParam("userId") int userId, @RequestParam("toUId") int toUId) {
        List<FollowVO> followVOList = followService.getFansByUId(userId, toUId);
        return ResponseResult.success(followVOList);
    }
}

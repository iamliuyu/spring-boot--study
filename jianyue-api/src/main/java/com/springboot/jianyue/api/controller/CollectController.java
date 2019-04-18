package com.springboot.jianyue.api.controller;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.Collect;
import com.springboot.jianyue.api.entity.vo.ArticleVO;
import com.springboot.jianyue.api.service.CollectService;
import com.springboot.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/collect")
public class CollectController {
    @Resource
    private CollectService collectService;

    @PostMapping("/add")
    public ResponseResult addImg(@RequestParam("uId") int uId, @RequestParam("aId") int aId) {
        Collect collect = new Collect();
        collect.setUId(uId);
        collect.setAId(aId);
        collectService.insertCollect(collect);
        return ResponseResult.success();
    }

    @GetMapping(value = "/")
    public ResponseResult getAllCollect(@RequestParam("userId") int userId) {
        List<ArticleVO> articleList = collectService.selectCollectsByUId(userId);
        return ResponseResult.success(articleList);
    }
}

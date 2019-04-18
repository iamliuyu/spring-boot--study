package com.springboot.jianyue.api.service;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.Collect;
import com.springboot.jianyue.api.entity.vo.ArticleVO;

import java.util.List;

public interface CollectService {
    List<ArticleVO> selectCollectsByUId(int uId);

    void insertCollect(Collect collect);
}

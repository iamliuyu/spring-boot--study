package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.Collect;
import com.springboot.jianyue.api.entity.vo.ArticleVO;
import com.springboot.jianyue.api.mapper.ArticleMapper;
import com.springboot.jianyue.api.mapper.ColloectMapper;
import com.springboot.jianyue.api.mapper.CommentMapper;
import com.springboot.jianyue.api.mapper.ImgMapper;
import com.springboot.jianyue.api.service.CollectService;
import com.springboot.jianyue.api.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private ColloectMapper colloectMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<ArticleVO> selectCollectsByUId(int uId) {
        List<Collect> collects = colloectMapper.selectCollectsByAId(uId);
        List<ArticleVO> articles = new ArrayList<ArticleVO>();
        for (Collect collect:collects) {
            articles.add(articleMapper.getArticleById(collect.getAId()));
        }
        for (ArticleVO articleVO:articles) {
            //处理时间
            articleVO.setCreateTime(articleVO.getCreateTime().substring(0, articleVO.getCreateTime().length()-2));
            //过滤md标签
            articleVO.setContent(StringUtil.handleContent(articleVO.getContent()));
            //获取图片
            articleVO.setImgs(imgMapper.selectImgsByAId(articleVO.getId()));
            //获取评论数
            articleVO.setCommentCount(commentMapper.selectCommentsByAId(articleVO.getId()).size());
        }
        return articles;
    }

    @Override
    public void insertCollect(Collect collect) {
        colloectMapper.insertCollect(collect);
    }
}

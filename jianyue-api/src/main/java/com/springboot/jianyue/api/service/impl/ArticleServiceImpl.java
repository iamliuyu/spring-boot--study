package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.vo.ArticleVO;
import com.springboot.jianyue.api.mapper.ArticleMapper;
import com.springboot.jianyue.api.mapper.CommentMapper;
import com.springboot.jianyue.api.mapper.ImgMapper;
import com.springboot.jianyue.api.service.ArticleService;
import com.springboot.jianyue.api.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ImgMapper imgMapper;
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<ArticleVO> selectAll() {
        List<ArticleVO> articles = articleMapper.selectAll();
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
    public List<Article> selectAllArticle() {
        return articleMapper.selectAllArticle();
    }

    @Override
    public ArticleVO getArticleById(int aId) {
        return articleMapper.getArticleById(aId);
    }

    @Override
    public void insertArticle(Article article) {
        articleMapper.insertArticle(article);
    }

    @Override
    public List<ArticleVO> getArticleListById(int uId) {
        return articleMapper.getArticleListById(uId);
    }

    @Override
    public void deleteArticle(int id) {
        articleMapper.deleteArticle(id);
    }
}

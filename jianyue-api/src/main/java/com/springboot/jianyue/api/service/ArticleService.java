package com.springboot.jianyue.api.service;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.vo.ArticleVO;

import java.util.List;

public interface ArticleService {
    List<ArticleVO> selectAll();

    List<Article> selectAllArticle();

    ArticleVO getArticleById(int aId);

    void insertArticle(Article article);

    List<ArticleVO> getArticleListById(int uId);

    void deleteArticle(int id);
}

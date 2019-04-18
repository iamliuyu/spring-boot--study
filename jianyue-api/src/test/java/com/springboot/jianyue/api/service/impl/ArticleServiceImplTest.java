package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.vo.ArticleVO;
import com.springboot.jianyue.api.mapper.ArticleMapper;
import com.springboot.jianyue.api.service.ArticleService;
import com.springboot.jianyue.api.util.poi.PoiUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleService articleService;

    @Test
    public void selectAll() {
        List<ArticleVO> list = articleService.selectAll();
        System.out.println(list);
    }

    @Test
    public void getArticleById() {
        ArticleVO articleVO = articleService.getArticleById(2);
        System.out.println(articleVO);
    }

    @Test
    public void deleteArticleById() {
        for (int i =126; i < 1126; i++) {
            articleService.deleteArticle(i);
        }
    }

    @Test
    public void insertArticle() {
        for (int i =0; i < 200; i++) {
            Article article = new Article();
            article.setTitle(Integer.toString(i));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            article.setContent(sdf.format(new Date()));
            article.setCreateTime(new Date());
            article.setUId(1);
            articleService.insertArticle(article);
        }
    }

//    @Test
//    public void exportArticle() {
//            PoiUtils poiUtils = new PoiUtils();
//            poiUtils.exportArticleExcel(articleMapper.selectAllArticle());
//    }
}
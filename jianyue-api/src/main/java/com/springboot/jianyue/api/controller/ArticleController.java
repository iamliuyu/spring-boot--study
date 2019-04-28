package com.springboot.jianyue.api.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.Follow;
import com.springboot.jianyue.api.entity.vo.ArticleVO;
import com.springboot.jianyue.api.entity.vo.CommentVO;
import com.springboot.jianyue.api.service.ArticleService;
import com.springboot.jianyue.api.service.CommentService;
import com.springboot.jianyue.api.service.FollowService;
import com.springboot.jianyue.api.service.ImgService;
import com.springboot.jianyue.api.util.MsgConst;
import com.springboot.jianyue.api.util.ResponseResult;
import com.springboot.jianyue.api.util.StringUtil;
import com.springboot.jianyue.api.util.poi.PoiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private ImgService imgService;
    @Resource
    private FollowService followService;

    @GetMapping(value = "/list")
    public ResponseResult getAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<ArticleVO> articleVOPageInfo = new PageInfo<>(articleService.selectAll());
        return ResponseResult.success(articleVOPageInfo);
    }

//    @GetMapping(value = "/{aId}")
//    public ResponseResult getArticleById(@PathVariable("aId") int aId) {
//        ArticleVO article = articleService.getArticleById(aId);
//        List<CommentVO> comments = commentService.selectCommentsByAId(aId);
//        List<Img> imgs = imgService.selectImgsByAId(aId);
//        Map<String, Object> map = new HashMap<>();
//        map.put("article", article);
//        map.put("comments", comments);
//        map.put("imgs", imgs);
//        return ResponseResult.success(map);
//    }

    @GetMapping(value = "/{aId}")
    public ResponseResult getArticleById(@PathVariable("aId") int aId,@RequestParam("userId") int userId) {
        ArticleVO article = articleService.getArticleById(aId);
        article.setCreateTime(article.getCreateTime().substring(0, article.getCreateTime().length()-2));
        int toUId = article.getUId();
        Map<String, Object> map = new HashMap<>();
        Follow follow = followService.getFollow(userId, toUId);
        if (follow != null) {
            map.put("followed", MsgConst.FOLLOWED);
        } else {
            map.put("followed", MsgConst.NO_FOLLOWED);
        }
        List<CommentVO> comments = commentService.selectCommentsByAId(aId);
        for (CommentVO commentVO:comments) {
            //处理时间
            commentVO.setCommentTime(commentVO.getCommentTime().substring(0, commentVO.getCommentTime().length()-2));
        }
        map.put("article", article);
        map.put("comments", comments);
        //文章字数
        int contentCount = StringUtil.handleContent(article.getContent()).length();
        map.put("contentCount", contentCount);
        return ResponseResult.success(map);
    }

    @GetMapping(value = "/user")
    public ResponseResult getArticleListById(@RequestParam("userId") int userId) {
        List<ArticleVO> articleList = articleService.getArticleListById(userId);
        for (ArticleVO articleVO:articleList) {
            //处理时间
            articleVO.setCreateTime(articleVO.getCreateTime().substring(0, articleVO.getCreateTime().length()-2));
            //处理内容
            articleVO.setContent(StringUtil.handleThumbnailContent(articleVO.getContent()));
            //获取评论数
            articleVO.setCommentCount(commentService.selectCommentsByAId(articleVO.getId()).size());
        }
        return ResponseResult.success(articleList);
    }

    @PostMapping("/add")
    public ResponseResult postArticle(@RequestParam("uId") int uId,
                                      @RequestParam("title") String title,
                                      @RequestParam("content") String content) {
        Article article = new Article();
        article.setUId(uId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Date());
        articleService.insertArticle(article);
        //新增文章后，将获取到的自增主键返回给客户端，方便图片地址的写入
        return ResponseResult.success(article.getId());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseResult deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
        return ResponseResult.success();
    }

    @RequestMapping(value = "/exportArticle", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportArticle() {
        return PoiUtils.exportArticleExcel(articleService.selectAllArticle());
    }
}

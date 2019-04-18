package com.springboot.jianyue.api.mapper;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "u_id", property = "uId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "avatar", property = "avatar"),
    })
    @Select("SELECT t_article.*,t_user.`nickname`,t_user.`avatar` " +
            "FROM t_article LEFT JOIN t_user ON t_article.u_id = t_user.id ORDER BY t_article.create_time DESC ")
    List<ArticleVO> selectAll();

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "u_id", property = "uId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "create_time", property = "createTime"),
    })
    @Select("SELECT * FROM t_article ORDER BY t_article.create_time DESC ")
    List<Article> selectAllArticle();

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "u_id", property = "uId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "avatar", property = "avatar"),
    })
    @Select("SELECT t_article.*,t_user.`nickname`,t_user.`avatar` " +
            "FROM t_article LEFT JOIN t_user ON t_article.u_id = t_user.id " +
            "WHERE t_article.id = #{aId}")
    ArticleVO getArticleById(int aId);

    @Insert("INSERT INTO t_article (u_id,title,content,create_time) VALUES (#{uId},#{title},#{content},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertArticle(Article article);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "u_id", property = "uId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "avatar", property = "avatar"),
    })
    @Select("SELECT t_article.*,t_user.`nickname`,t_user.`avatar` " +
            "FROM t_article LEFT JOIN t_user ON t_article.u_id = t_user.id WHERE u_id = #{uId} ORDER BY t_article.create_time DESC ")
    List<ArticleVO> getArticleListById(int uId);

    @Delete("DELETE FROM t_article WHERE id = #{id} ")
    void deleteArticle(@Param("id") int id);
}

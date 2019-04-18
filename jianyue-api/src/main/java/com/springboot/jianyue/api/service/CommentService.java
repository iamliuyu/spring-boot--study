package com.springboot.jianyue.api.service;

import com.springboot.jianyue.api.entity.Comment;
import com.springboot.jianyue.api.entity.vo.CommentVO;

import java.util.List;

public interface CommentService {
    List<CommentVO> selectCommentsByAId(int aId);

    void addComment(Comment comment);
}
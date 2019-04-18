package com.springboot.jianyue.api.service.impl;

import com.springboot.jianyue.api.entity.Comment;
import com.springboot.jianyue.api.entity.vo.CommentVO;
import com.springboot.jianyue.api.mapper.CommentMapper;
import com.springboot.jianyue.api.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Override
    public List<CommentVO> selectCommentsByAId(int aId) {
        return commentMapper.selectCommentsByAId(aId);
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }
}

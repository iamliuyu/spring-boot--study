package com.springboot.mybatis.service.impl;

import com.springboot.mybatis.entity.Course;
import com.springboot.mybatis.entity.CourseVO;
import com.springboot.mybatis.mapper.CourseMapper;
import com.springboot.mybatis.mapper.CourseVOMapper;
import com.springboot.mybatis.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseVOMapper courseVOMapper;

    @Override
    public List<CourseVO> selectAll() {
        return courseVOMapper.selectAll();
    }

    @Override
    public List<CourseVO> selectEndAll() {
        return courseVOMapper.selectEndAll();
    }

    @Override
    public Course getOne(long courseId) {
        return courseMapper.getOne(courseId);
    }

    @Override
    public void delete(long courseId) {
        courseMapper.delete(courseId);
    }

    @Override
    public Course insert(Course course) {
        courseMapper.insert(course);
        return course;
    }

    @Override
    public void update(Course course) {
        courseMapper.update(course);
    }
}

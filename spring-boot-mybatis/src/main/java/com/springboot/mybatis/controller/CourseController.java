package com.springboot.mybatis.controller;

import com.springboot.mybatis.entity.Course;
import com.springboot.mybatis.entity.CourseVO;
import com.springboot.mybatis.service.CourseService;
import com.springboot.mybatis.uitl.RandomUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CourseController {
    @Resource
    private CourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<CourseVO> selectAll() {
        return courseService.selectAll();
    }

    @RequestMapping(value = "/end_courses", method = RequestMethod.GET)
    public List<CourseVO> selectEndAll() {
        return courseService.selectEndAll();
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public Course getOne(@PathVariable("id") long id) {
        return courseService.getOne(id);
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("id") long id) {
        courseService.delete(id);
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public Course addCourse(@RequestBody Course course) {
        course.setCourseCode(RandomUtil.getRandomCode());
        return courseService.insert(course);
    }

    @RequestMapping(value = "/course", method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course) {
        courseService.update(course);
    }
}

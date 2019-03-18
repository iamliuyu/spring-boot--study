package com.springboot.mybatis.service.impl;

        import com.springboot.mybatis.entity.Course;
        import com.springboot.mybatis.service.CourseService;
        import com.springboot.mybatis.uitl.RandomUtil;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import javax.annotation.Resource;

        import java.util.List;
        import java.util.Random;

        import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {
    @Resource
    private CourseService courseService;

    @Test
    public void selectAll() {
        List<Course> courseList = courseService.selectAll();
        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void getOne() {
        Course course = courseService.getOne(1L);
        System.out.println(course);
    }

    @Test
    public void delete() {
        courseService.delete(9L);
    }

    @Test
    public void insert() {
        Course course = new Course();
        course.setCourseName("微信小程序开发");
        course.setCourseName("软件1721");
        course.setUserId(1L);
        course.setCover("1.jpg");
        course.setCourseCode(RandomUtil.getRandomCode());
        course.setFinished((short) 0);
        courseService.insert(course);
    }

    @Test
    public void update() {
        Course course = courseService.getOne(12L);
        course.setCover("cover.jpg");
        course.setFinished((short) 0);
        courseService.update(course);
    }
}
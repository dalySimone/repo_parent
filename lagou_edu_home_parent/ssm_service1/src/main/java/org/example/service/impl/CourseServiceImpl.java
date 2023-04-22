package org.example.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.example.bean.Course;
import org.example.bean.CourseVO;
import org.example.bean.Teacher;
import org.example.dao.CourseMapper;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        List<Course> list = courseMapper.findCourseByCondition(courseVO);
        return list;
    }

    @Override
    public void saveCourseAndTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {


            // 封装course
            Course course = new Course();
            BeanUtils.copyProperties(course, courseVO);

            // 补全信息
            Date date = new Date();
            course.setUpdateTime(date);
            course.setCreateTime(date);

            courseMapper.saveCourse(course);

            // 获取courseId
            int id = course.getId();
            // 封装teacher
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher, courseVO);

            // 补全信息
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setCourseId(id);

            courseMapper.saveTeacher(teacher);


    }

    @Override
    public CourseVO findCourseById(Integer id) {

        CourseVO courseVO = courseMapper.findCourseById(id);
        return courseVO;
    }

    @Override
    public void updateCourseAndTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        Course course = new Course();
        BeanUtils.copyProperties(course, courseVO);

        // 补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        // 修改课程
        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVO);

        // 补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        // 修改讲师
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id, Integer status) {

        //封装实体
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        // 修改状态
        courseMapper.updateCourseStatus(course);
    }
}

package org.example.service.impl;

import org.example.bean.Course;
import org.example.bean.CourseLesson;
import org.example.bean.CourseSection;
import org.example.dao.CourseContentMapper;
import org.example.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findCourseSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findCourseSectionAndLessonById(courseId);
        return list;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public void saveCourseSection(CourseSection courseSection) {

        // 补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        // 新增章节
        courseContentMapper.saveCourseSection(courseSection);
    }

    @Override
    public void updateCourseSection(CourseSection courseSection) {

        // 补全信息
        courseSection.setUpdateTime(new Date());

        // 修改章节
        courseContentMapper.updateCourseSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {

        // 封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        // 修改状态
        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveCourseLesson(CourseLesson courseLesson) {

        // 封装数据
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        // 保存课时
        courseContentMapper.saveCourseLesson(courseLesson);
    }
}

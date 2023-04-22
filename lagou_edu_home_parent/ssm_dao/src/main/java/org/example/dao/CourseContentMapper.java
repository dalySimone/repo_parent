package org.example.dao;

import org.example.bean.Course;
import org.example.bean.CourseLesson;
import org.example.bean.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*查询章节信息以及课时信息*/
    public List<CourseSection> findCourseSectionAndLessonById(Integer classId);

    /*回显章节课程信息*/
    public Course findCourseByCourseId(int courseId);

    /*新增章节信息*/
    public void saveCourseSection(CourseSection courseSection);

    /*修改章节信息*/
    public void updateCourseSection(CourseSection courseSection);

    /*修改章节状态*/
    public void updateSectionStatus(CourseSection courseSection);

    /*新增课时信息*/
    public void saveCourseLesson(CourseLesson courseLesson);


}

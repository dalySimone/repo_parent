package org.example.dao;

import org.example.bean.Course;
import org.example.bean.CourseVO;
import org.example.bean.Teacher;

import java.util.List;

public interface CourseMapper {

    /*根据条件查询*/
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*保存课程信息*/
    public void saveCourse(Course course);

    /*保存讲师信息*/
    public void saveTeacher(Teacher teacher);

    /*根据id查询课程及讲师信息*/
    public CourseVO findCourseById(Integer id);

    /*修改课程信息*/
    public void updateCourse(Course course);

    /*修改讲师信息*/
    public void updateTeacher(Teacher teacher);

    /*修改课程状态*/
    public void updateCourseStatus(Course course);
}

package org.example.service;

import org.example.bean.Course;
import org.example.bean.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    public List<Course> findCourseByCondition(CourseVO courseVO);

    public void saveCourseAndTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    public CourseVO findCourseById(Integer id);

    public void updateCourseAndTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    public void updateCourseStatus(Integer id, Integer status);

}

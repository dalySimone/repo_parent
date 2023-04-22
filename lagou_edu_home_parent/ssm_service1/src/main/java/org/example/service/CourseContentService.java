package org.example.service;

import org.example.bean.Course;
import org.example.bean.CourseLesson;
import org.example.bean.CourseSection;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findCourseSectionAndLessonByCourseId(Integer courseId);

    public Course findCourseByCourseId(int courseId);

    public void saveCourseSection(CourseSection courseSection);

   public void updateCourseSection(CourseSection courseSection);

   public void updateSectionStatus(int id, int status);

   public void saveCourseLesson(CourseLesson courseLesson);
}

package org.example.controller;

import com.mysql.fabric.Response;
import org.example.bean.Course;
import org.example.bean.CourseLesson;
import org.example.bean.CourseSection;
import org.example.bean.ResponseResult;
import org.example.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findCourseSectionAndLessonByCourseId(Integer courseId){

        List<CourseSection> list = courseContentService.findCourseSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;

    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        if (courseSection == null){
            courseContentService.saveCourseSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增章节成功", null);
            return responseResult;
        }else{
            courseContentService.updateCourseSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改章节成功", null);
            return responseResult;
        }
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status){

        courseContentService.updateSectionStatus(id, status);

        Map<Object, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveCourseLesson(@RequestBody CourseLesson courseLesson){

        courseContentService.saveCourseLesson(courseLesson);

        ResponseResult responseResult = new ResponseResult(true, 200, "保存课时信息成功", null);
        return responseResult;
    }

}

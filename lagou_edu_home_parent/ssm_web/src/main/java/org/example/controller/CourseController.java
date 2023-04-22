package org.example.controller;

import org.example.bean.Course;
import org.example.bean.CourseVO;
import org.example.bean.ResponseResult;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(MultipartFile file, HttpServletRequest request){

        // 1.判断文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        // 2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String webPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        // 3.获取源文件名
        String filename = file.getOriginalFilename();

        // 4.新文件名
        String newFileName = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        // 5.上传文件
        String uploadPath = webPath+"upload\\";
        File filePath = new File(uploadPath, newFileName);
        // 如果文件不存在就创建一个
        if (filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {


        if (courseVO.getId() == null){
            courseService.saveCourseAndTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "保存成功", null);
            return responseResult;
        }else{
            courseService.updateCourseAndTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }


    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam Integer id){

        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVO);
        return responseResult;
    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus( Integer id, Integer status){

        courseService.updateCourseStatus(id, status);

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

}

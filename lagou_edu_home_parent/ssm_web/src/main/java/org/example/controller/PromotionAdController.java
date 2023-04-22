package org.example.controller;

import com.github.pagehelper.PageInfo;
import org.example.bean.PromotionAd;
import org.example.bean.PromotionAdVO;
import org.example.bean.ResponseResult;
import org.example.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAd(PromotionAdVO promotionAdVO){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAd(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询数据成功", pageInfo);
        return responseResult;
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(MultipartFile file, HttpServletRequest request){

        // 1.判断文件是否为空
        if(file.isEmpty()){
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

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status){

        promotionAdService.updatePromotionAdStatus(id, status);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告上下线成功", null);
        return responseResult;

    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if (promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else{
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改广告信息成功", null);
            return responseResult;
        }

    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){

        PromotionAd list = promotionAdService.findPromotionAdById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询广告成功", list);
        return responseResult;
    }
}

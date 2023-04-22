package org.example.controller;

import com.github.pagehelper.PageInfo;
import org.example.bean.Resource;
import org.example.bean.ResourceVO;
import org.example.bean.ResponseResult;
import org.example.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVO resourceVO){

        PageInfo<Resource> list = resourceService.findAllResourceByPage(resourceVO);
        return new ResponseResult(true, 200, "查询所有资源成功&条件" , list);
    }

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        if (resource.getId() == null ){

            resourceService.saveResource(resource);
            return new ResponseResult(true, 200, "添加资源信息成功", null);
        }else{
            resourceService.updateResource(resource);
            return new ResponseResult(true, 200, "修改资源信息成功", null);
        }
    }

    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){

        resourceService.deleteResource(id);
        return new ResponseResult(true, 200, "删除资源成功", null);
    }

    @RequestMapping("/findResourceById")
    public ResponseResult findResourceById(Integer id){

        Resource resource = resourceService.findResourceById(id);
        return new ResponseResult(true, 200, "回显资源信息成功", resource);
    }
}

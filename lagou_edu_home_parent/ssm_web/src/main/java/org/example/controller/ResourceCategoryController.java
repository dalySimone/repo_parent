package org.example.controller;

import org.example.bean.ResourceCategory;
import org.example.bean.ResponseResult;
import org.example.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true, 200, "查询所有资源分类成功", list);
    }
}

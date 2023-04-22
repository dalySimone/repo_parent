package org.example.service.impl;

import org.example.bean.ResourceCategory;
import org.example.dao.ResourceCategoryMapper;
import org.example.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();
        return list;
    }
}

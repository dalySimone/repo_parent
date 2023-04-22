package org.example.dao;

import org.example.bean.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /*查询资源分类信息*/
    public List<ResourceCategory> findAllResourceCategory();
}

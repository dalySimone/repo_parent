package org.example.dao;

import org.example.bean.Resource;
import org.example.bean.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /*查询所有资源*/
    public List<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /*添加资源信息*/
    public void saveResource(Resource resource);

    /*修改资源信息*/
    public void updateResource(Resource resource);

    /*删除资源/*/
    public void deleteResource(int id);

    /*根据id查询资源*/
    public Resource findResourceById(int id);
}

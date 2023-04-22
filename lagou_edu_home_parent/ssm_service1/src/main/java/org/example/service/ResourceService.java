package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.bean.Resource;
import org.example.bean.ResourceVO;
import org.example.dao.ResourceMapper;

import java.util.List;

public interface ResourceService {

    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);

    public void saveResource(Resource resource);

    public void updateResource(Resource resource);

    public void deleteResource(Integer id);

    public Resource findResourceById(Integer id);
}

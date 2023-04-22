package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.bean.Resource;
import org.example.bean.ResourceVO;
import org.example.dao.ResourceMapper;
import org.example.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {

        PageHelper.startPage(resourceVO.getCurrentPage(), resourceVO.getPageSize());

        List<Resource> list = resourceMapper.findAllResourceByPage(resourceVO);
        PageInfo<Resource> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {

        // 封装数据
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        // 调用mapper
        resourceMapper.saveResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {

        // 封装数据
        resource.setUpdatedTime(new Date());

        // 调用mapper
        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(Integer id) {

        resourceMapper.deleteResource(id);
    }

    @Override
    public Resource findResourceById(Integer id) {

        Resource resourceById = resourceMapper.findResourceById(id);
        return resourceById;
    }
}

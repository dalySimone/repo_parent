package org.example.service.impl;

import org.example.bean.Menu;
import org.example.dao.MenuMapper;
import org.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenuByPid(int pid) {

        List<Menu> list = menuMapper.findAllMenuByPid(pid);
        return list;
    }

    @Override
    public List<Menu> findAllMen() {
        List<Menu> list = menuMapper.findAllMenu();
        return list;
    }

    @Override
    public Menu findMenuById(Integer id) {

        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    @Override
    public void saveMenu(Menu menu) {

        // 封装数据
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        // 调用mapper
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

        // 封装数据
        menu.setUpdatedTime(new Date());

        // 调用mapper
        menuMapper.updateMenu(menu);
    }
}

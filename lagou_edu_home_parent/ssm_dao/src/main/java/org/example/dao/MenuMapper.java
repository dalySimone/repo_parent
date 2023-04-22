package org.example.dao;

import org.example.bean.Menu;

import java.util.List;

public interface MenuMapper {

    /*查询所有菜单列表*/
    public List<Menu> findAllMenuByPid(Integer pid);

    /*查询菜单列表*/
    public List<Menu> findAllMenu();

    /*根据id查询菜单*/
    public Menu findMenuById(Integer id);

    /*添加菜单*/
    public void saveMenu(Menu menu);

    /*修改菜单*/
    public void updateMenu(Menu menu);
}

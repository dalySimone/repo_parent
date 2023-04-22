package org.example.service;

import org.example.bean.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> findAllMenuByPid(int pid);

    public List<Menu> findAllMen();

    public Menu findMenuById(Integer id);

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);
}

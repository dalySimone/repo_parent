package org.example.controller;

import org.example.bean.Menu;
import org.example.bean.ResponseResult;
import org.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> list = menuService.findAllMen();
        return new ResponseResult(true, 200, "查询所有菜单列表成功", list);
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        if (id == -1){
            List<Menu> list = menuService.findAllMenuByPid(id);

            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", list);
            return new ResponseResult(true, 200, "新建菜单回显信息成功", map);
        }else{
            Menu menu = menuService.findMenuById(id);

            List<Menu> list = menuService.findAllMenuByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", list);
            return new ResponseResult(true, 200, "修改菜单回显信息成功", map);
        }
    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){

        if ( menu.getId() == null){

            menuService.saveMenu(menu);
            return new ResponseResult(true, 200, "添加菜单成功", null);
        }else{
            menuService.updateMenu(menu);
            return new ResponseResult(true, 200, "修改菜单成功", null);
        }
    }
}

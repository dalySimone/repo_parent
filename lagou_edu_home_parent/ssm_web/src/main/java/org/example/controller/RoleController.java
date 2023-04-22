package org.example.controller;

import com.mysql.fabric.Response;
import org.example.bean.Menu;
import org.example.bean.ResponseResult;
import org.example.bean.Role;
import org.example.bean.RoleMenuVO;
import org.example.service.MenuService;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> list = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色信息成功", list);
        return responseResult;

    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> list = menuService.findAllMenuByPid(-1);

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", list);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单列表成功", map);
        return responseResult;
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> list = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联菜单信息成功", list);
        return responseResult;
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVo){

        roleService.roleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "角色分配菜单成功", null);
        return responseResult;
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){

        if (role.getId() == null){
            roleService.saveRole(role);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else{
            roleService.updateRole(role);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    @RequestMapping("/findRoleById")
    public ResponseResult findRoleById(Integer id){

        Role role = roleService.findRoleById(id);
        return new ResponseResult(true, 200, "根据id回显角色信息成功", role);

    }
}

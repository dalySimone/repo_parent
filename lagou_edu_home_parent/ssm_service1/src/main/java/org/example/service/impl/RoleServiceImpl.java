package org.example.service.impl;

import org.example.bean.Menu;
import org.example.bean.Role;
import org.example.bean.RoleMenuVO;
import org.example.bean.Role_menu_relation;
import org.example.dao.RoleMapper;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        for (Integer mid : roleMenuVO.getMenuIdList()) {

            // 封装数据
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.saveRoleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(int roleId) {

        // 清空角色所关联的菜单信息
        roleMapper.deleteRoleContextMenu(roleId);

        // 删除角色信息
        roleMapper.deleteRole(roleId);
    }

    @Override
    public void saveRole(Role role) {

        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public Role findRoleById(Integer id) {

        Role role = roleMapper.findRoleById(id);
        return role;
    }
}

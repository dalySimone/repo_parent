package org.example.dao;

import org.example.bean.Role;
import org.example.bean.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*查询所有角色信息*/
    public List<Role> findAllRole(Role role);

    /*查询角色关联的菜单信息*/
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*根据roleId删除角色关联菜单信息*/
    public void deleteRoleContextMenu(int roleId);

    /*为角色关联菜单信息*/
    public void saveRoleContextMenu(Role_menu_relation role_menu_relation);

    /*删除角色*/
    public void deleteRole(Integer roleId);

    /*添加角色*/
    public void saveRole(Role role);

    /*修改角色*/
    public void updateRole(Role role);

    /*根据id查询角色信息*/
    public Role findRoleById(Integer id);
}

package org.example.dao;

import org.example.bean.*;

import java.util.List;

public interface UserMapper {

    /*多条件查询用户信息并且分页显示*/
    public List<User> findAllUserByPage(UserVO userVO);

    /*修改用户状态信息*/
    public void updateUserStatus(User user);

    /*根据用户名查询密码*/
    public User login(User user);

    /*查询用户关联的角色信息*/
    public List<Role> findUserRoleById(Integer id);

    /*根据id删除用户对应角色信息/*/
    public void deleteUserContextRole(Integer userId);

    /*为用户分配角色信息*/
    public void userContextRole(User_Role_relation user_role_relation);

    /*根据用户关联的角色信息查询父级菜单信息*/
    public List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    /*根据父级id查询子级菜单信息*/
    public List<Menu> findSubMenuByPid(Integer pid);

    /*根据用户关联的角色信息查询资源信息*/
    public List<Resource> findResourceByRoleId(List<Integer> roleIds);
}

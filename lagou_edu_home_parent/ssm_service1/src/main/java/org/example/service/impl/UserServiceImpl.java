package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.bean.*;
import org.example.dao.UserMapper;
import org.example.service.UserService;
import org.example.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVO);

        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {

        // 封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        // 调用mapper
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {

        User user1 = userMapper.login(user);

        if (user1 != null && Md5.verify(user.getPassword(), "org", user1.getPassword())){
            return user1;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRoleById(Integer id) {

        List<Role> list = userMapper.findUserRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVO userVO) {

        // 删除用户关联角色关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        for (Integer roleId : userVO.getRoleIdList()) {

            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setRoleId(roleId);
            user_role_relation.setUserId(userVO.getUserId());

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    // 查询用户所有权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        // 查询所有角色信息
        List<Role> roleList = userMapper.findUserRoleById(userId);

        List<Integer> roleIds = new ArrayList<>();

        // 将所有角色id存入集合
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        // 根据角色id查询父级菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        // 根据父级id查询自己菜单并封装到subMenuList中
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 根据角色id查询资源信息
        List<Resource> resources = userMapper.findResourceByRoleId(roleIds);

        Map<String, Object> map  = new HashMap<>();
        // 将返回数据存到map中
        map.put("menuList", parentMenu);
        map.put("resourceList", resources);

        // 返回数据
        return new ResponseResult(true, 200, "查询权限信息成功", map);

    }
}

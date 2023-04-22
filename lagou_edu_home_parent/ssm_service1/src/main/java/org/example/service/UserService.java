package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.bean.ResponseResult;
import org.example.bean.Role;
import org.example.bean.User;
import org.example.bean.UserVO;

import java.util.List;

public interface UserService {

    public PageInfo findAllUserByPage(UserVO userVO);

    public void updateUserStatus(int id, String status);

    public User login(User user) throws Exception;

    public List<Role> findUserRoleById(Integer id);

    public void userContextRole(UserVO userVO);

    // 获取用户所有权限
    public ResponseResult getUserPermissions(Integer userId);
}

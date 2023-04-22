package org.example.controller;

import com.github.pagehelper.PageInfo;
import com.mysql.fabric.Response;
import org.example.bean.ResponseResult;
import org.example.bean.Role;
import org.example.bean.User;
import org.example.bean.UserVO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){

        PageInfo<User> pageInfo = userService.findAllUserByPage(userVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询用户数据成功", pageInfo);
        return responseResult;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status){

        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改用户状态成功", null);
        return responseResult;

    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request){

        try {
            User user1 = userService.login(user);
            if (user1 != null){

                HttpSession session = request.getSession();
                String  access_token = UUID.randomUUID().toString();
                session.setAttribute("access_token", access_token);
                session.setAttribute("user_id", user1.getId());

                Map<String , Object> map = new HashMap<>();
                map.put("access_token", access_token);
                map.put("user_id", user1.getId());
                map.put("user", user1);

                return new ResponseResult(true, 1, "登陆成功", map);

            }else {
                return new ResponseResult(true, 400, "用户名或者密码错误", null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id){

        List<Role> list = userService.findUserRoleById(id);
        return new ResponseResult(true, 200, "查询用户关联角色信息成功", list);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);
        return new ResponseResult(true, 200, "用户关联角色成功", null);

    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        String access_token = (String) request.getSession().getAttribute("access_token");

        if (authorization.equals(access_token)){

            Integer userId = (Integer) request.getSession().getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(userId);
            return responseResult;
        }else {
            return new ResponseResult(false, 400, "获取失败", null);
        }

    }

}

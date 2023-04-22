package org.example.service;

import org.example.bean.Menu;
import org.example.bean.Role;
import org.example.bean.RoleMenuVO;

import java.util.List;

public interface RoleService {

    public List<Role> findAllRole(Role role);

    public List<Integer> findMenuByRoleId(Integer roleId);

    public void roleContextMenu(RoleMenuVO roleMenuVO);

    public void deleteRole(int roleId);

    public void saveRole(Role role);

    public void updateRole(Role role);

    public Role findRoleById(Integer id);
}

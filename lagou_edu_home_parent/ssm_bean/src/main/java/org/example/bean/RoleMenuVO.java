package org.example.bean;

import java.util.List;

public class RoleMenuVO {

    private int roleId;
    private List<Integer> menuIdList;

    @Override
    public String toString() {
        return "RoleMenuVO{" +
                "roleId=" + roleId +
                ", menuIdList=" + menuIdList +
                '}';
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}

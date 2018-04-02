package com.echisan.model.dto;

import com.echisan.model.po.Permission;
import com.echisan.model.po.Role;
import com.echisan.model.po.User;

import java.util.List;

public class ActiveUserDTO {
    private User user;
    private Role role;
    private List<Permission> permissions;

    public ActiveUserDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "ActiveUserDTO{" +
                "user=" + user +
                ", role=" + role +
                ", permissions=" + permissions +
                '}';
    }
}

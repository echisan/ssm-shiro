package com.echisan.model.vo;

import com.echisan.model.po.Permission;
import com.echisan.model.po.Role;
import com.echisan.model.po.User;

import java.util.List;

/**
 * @author E73AN
 */
public class ActiveUser {

    private User user;
    private Role role;
    private List<Permission> permissions;

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
}

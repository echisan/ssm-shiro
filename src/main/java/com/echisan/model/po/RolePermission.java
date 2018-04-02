package com.echisan.model.po;

public class RolePermission {
    private Short id;

    private Byte roleId;

    private Byte permissionId;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Byte getRoleId() {
        return roleId;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public Byte getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Byte permissionId) {
        this.permissionId = permissionId;
    }
}
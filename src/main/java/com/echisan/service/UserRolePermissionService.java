package com.echisan.service;

import com.echisan.model.po.Permission;
import com.echisan.model.po.Role;
import com.echisan.model.po.RolePermission;

import java.util.List;

/**
 * @author E73AN
 */
public interface UserRolePermissionService {

    /**
     * 添加角色
     * @param role 角色
     */
    void saveRole(Role role);

    /**
     * 添加权限信息
     * @param permission 权限
     */
    void savePermission(Permission permission);

    /**
     * 给角色添加权限
     * @param roleId 目标角色
     * @param permissionId 目标权限
     */
    void saveRolePermission(Byte roleId, Byte permissionId);

    /**
     * 给角色添加多个权限
     * @param roleId 目标角色
     * @param permissionsId 目标权限们
     */
    void saveRolePermissions(Byte roleId, List<Byte> permissionsId);

    /**
     * 根据id删除角色
     * @param roleId id
     */
    void deleteRoleById(Byte roleId);

    /**
     * 根据id删除权限
     * @param permissionId id
     */
    void deletePermissionById(Byte permissionId);

    /**
     * 将该角色从角色权限表中中删除
     * @param roleId 角色id
     */
    void deleteRolePermissionByRoleId(Byte roleId);

    /**
     * 从该角色中删除该权限
     * @param roleId 角色id
     * @param permissionId 权限id
     */
    void deletePermissionFormRole(Byte roleId, Byte permissionId);


    /**
     * 更新角色
     * @param role id
     */
    void updateRoleById(Role role);

    /**
     * 更新权限
     * @param permission id
     */
    void updatePermissionById(Permission permission);

    /**
     * 更新关联表
     * @param roleId 角色id
     * @param permission 权限id
     */
    void updateRolePermission(Byte roleId,Permission permission);



    /**
     * 获取单个角色
     * @param roleId id
     * @return 角色实例
     */
    Role getRoleById(Byte roleId);

    /**
     * 获取所有的role
     * @return role列表
     */
    List<Role> listRoles();

    /**
     * 获取单个permission
     * @param permissionId id
     * @return permission
     */
    Permission getPermissionById(Byte permissionId);

    /**
     * 获取所有的permission
     * @return permission
     */
    List<Permission> listPermissions();

    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 用户权限列表
     */
    List<Permission> listUserPermissions(Long userId);

    /**
     * 获取用户的角色
     * @param userId 用户id
     * @return 角色
     */
    Role getUserRoleByUserId(Long userId);
}

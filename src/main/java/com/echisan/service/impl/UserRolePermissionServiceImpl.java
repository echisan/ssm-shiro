package com.echisan.service.impl;

import com.echisan.dao.PermissionMapper;
import com.echisan.dao.RoleMapper;
import com.echisan.dao.RolePermissionMapper;
import com.echisan.dao.UserRoleMapper;
import com.echisan.model.po.*;
import com.echisan.service.UserRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author E73AN
 */

@Service
@Transactional
public class UserRolePermissionServiceImpl implements UserRolePermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void saveRole(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void savePermission(Permission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void saveRolePermission(Byte roleId, Byte permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermissionMapper.insert(rolePermission);
    }

    @Override
    public void saveRolePermissions(Byte roleId, List<Byte> permissionsId) {
        RolePermission rolePermission;
        for (Byte permissionId :
                permissionsId) {
            rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Override
    public void deleteRoleById(Byte roleId) {
        // 从role表中删除
        roleMapper.deleteByPrimaryKey(roleId);

        // 从role_permission中删除
        deleteRolePermissionByRoleId(roleId);

    }

    @Override
    public void deletePermissionById(Byte permissionId) {

        // 从permission表中删除
        permissionMapper.deleteByPrimaryKey(permissionId);

        // 从role permission中删除
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionIdEqualTo(permissionId);

        rolePermissionMapper.deleteByExample(example);

    }


    @Override
    public void deleteRolePermissionByRoleId(Byte roleId) {
        // 从role_permission中删除
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);

        rolePermissionMapper.deleteByExample(example);
    }

    /**
     *
     * @param permissionId 权限id
     */
    @Override
    public void deletePermissionFormRole(Byte roleId, Byte permissionId) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId).andPermissionIdEqualTo(permissionId);
        rolePermissionMapper.deleteByExample(rolePermissionExample);
    }

    @Override
    public void updateRoleById(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void updatePermissionById(Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
    }


    @Override
    public void updateRolePermission(Byte roleId, Permission permission) {
        // 暂时不知道要干啥
    }

    @Override
    public Role getRoleById(Byte roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> listRoles() {
        RoleExample roleExample = new RoleExample();
        return roleMapper.selectByExample(roleExample);
    }


    @Override
    public Permission getPermissionById(Byte permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public List<Permission> listPermissions() {
        PermissionExample permissionExample = new PermissionExample();
        return permissionMapper.selectByExample(permissionExample);
    }

    @Override
    public List<Permission> listUserPermissions(Long userId) {
        Role role = getUserRoleByUserId(userId);

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(role.getId());

        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);

        List<Byte> permissionIds = new ArrayList<Byte>();

        for (RolePermission rolePermission:
                rolePermissions) {
            permissionIds.add(rolePermission.getPermissionId());
        }

        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria1 = permissionExample.createCriteria();
        criteria1.andIdIn(permissionIds);

        return permissionMapper.selectByExample(permissionExample);
    }

    @Override
    public Role getUserRoleByUserId(Long userId) {

        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles!=null && userRoles.size()!=0){
            return getRoleById(userRoles.get(0).getRoleId());
        }


        return null;
    }
}

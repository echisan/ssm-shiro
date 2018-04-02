package com.echisan.service;

import com.echisan.model.dto.ActiveUserDTO;
import com.echisan.model.po.*;
import java.util.List;

/**
 * @author E73AN
 */
public interface UserService {

    /**
     * LOCK 表示锁定
     * UNLOCK 表示解锁
     */
    Byte LOCK = 1;
    Byte UNLOCK = 0;

    /**
     * USER_TYPE_SUPER_ADMIN 超级管理员
     * USER_TYPE_NORMAL_ADMIN 普通管理员
     * USER_TYPE_NORMAL_ADMIN 普通用户
     */
    Byte USER_TYPE_SUPER_ADMIN = 1;
    Byte USER_TYPE_NORMAL_ADMIN = 2;
    Byte USER_TYPE_NORMAL_USER = 3;

    String SUPER_ADMIN = "super_admin";
    String NORMAL_ADMIN = "normal_admin";
    String NORMAL_USER = "normal_admin";

    /**
     * 用于通过注册添加新用户
     * @param user 注册时表单传送的数据由该pojo保存
     * @param userType 用户类型
     */
    void saveUser(User user, Byte userType);

    /**
     * 用于添加默认的用户，即：普通用户
     * @param user 用户
     */
    void saveDefaultUser(User user);

    /**
     * 用于通过用户id删除用户
     * @param userId 需要删除的用户的id
     */
    void deleteUserById(Long userId);

    /**
     * 用于通过用户username删除用户
     * @param username 需要删除的用户的用户名
     */
    void deleteUserByUsername(String username);

    /**
     * 通过用户id更新用户信息
     * @param user 用于更新用户信息的业务对象
     */
    void updateUser(User user);

    /**
     * 更新最后登录时间
     * @param userId user id
     */
    void updateUserLastLoginTimeById(Long userId);

    /**
     * 用于管理员对用户进行封禁
     * @param userLockInfo 实体
     * @param isLock 封禁或解封
     */
    void updateUserLockInfo(UserLockInfo userLockInfo, boolean isLock);

    /**
     * 由于该业务使用频率较高 所以分离出来
     * @param userId 需要更改密码的用户的id
     * @param newPassword 需要更改的新密码
     */
    void updateUserPassword(Long userId, String newPassword);

    /**
     * 获取所有已经认证了信息的用户
     * @return 返回已认证的用户的列表
     */
    List<User> listAuthUser();

    /**
     * 通过用户id获取用户的密码
     * @param userId 用户id
     * @return 返回用户实体
     */
    User getUserById(Long userId);

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return 用户实体
     */
    User getUserByName(String username);

    /**
     * 返回用户的权限信息
     * @param username 用户名
     * @return 包含用户信息以及权限信息的传输对象
     */
    ActiveUserDTO getActiveUserByName(String username);

    /**
     * 通过id获取信息
     * @param userId 用户id
     * @return 包含用户信息以及权限信息的传输对象
     */
    ActiveUserDTO getActiveUserById(Long userId);

    /**
     * 添加封禁信息
     * @param userLockInfo lock info
     */
    void saveUserLockInfo(UserLockInfo userLockInfo);

    /**
     * 删除封禁信息
     * @param id id
     */
    void deleteUserLockInfoById(Long id);

    /**
     * 更新封禁信息
     * @param userLockInfo 封禁实体
     */
    void updateUserLockInfo(UserLockInfo userLockInfo);

    /**
     * 获取user lock info
     * @param id userLockInfo id
     * @return userLockInfo
     */
    UserLockInfo getUerLockInfoById(Long id);


    /**
     * 获取用户的锁定信息
     * @param username 用户名
     * @return 锁定信息
     */
    List<UserLockInfo> listUserLockInfoByUsername(String username);

    /**
     * 获取用户的锁定信息
     * @param id id
     * @return lock info
     */
    List<UserLockInfo> listUserLockInfoByUserId(Long id);


    void saveUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoByUserId(Long userId);
}

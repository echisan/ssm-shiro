package com.echisan.service.impl;

import com.echisan.dao.UserInfoMapper;
import com.echisan.dao.UserLockInfoMapper;
import com.echisan.dao.UserMapper;
import com.echisan.dao.UserRoleMapper;
import com.echisan.model.bo.PasswordSaltUserBO;
import com.echisan.model.bo.RegisterUserBO;
import com.echisan.model.dto.ActiveUserDTO;
import com.echisan.model.po.*;
import com.echisan.service.UserRolePermissionService;
import com.echisan.service.UserService;
import com.echisan.util.LockStatus;
import com.echisan.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author E73AN
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLockInfoMapper userLockInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void saveUser(User user, Byte userType) {
        if (user.getUsername() != null && user.getPassword() != null
                && user.getSalt() != null) {
            user.setIsLock((byte) 0);
            userMapper.insertSelective(user);

            // 默认创建普通用户
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(userType);
            userRoleMapper.insertSelective(userRole);
        }else {
            logger.debug("username or password or salt can not be null.");
        }
    }

    @Override
    public void saveDefaultUser(User user) {

        if (user.getUsername() != null && user.getPassword() != null
                && user.getSalt() != null) {
//            UserExample userExample = new UserExample();
            userMapper.insertSelective(user);

            // 默认创建普通用户
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(USER_TYPE_NORMAL_USER);
            userRoleMapper.insertSelective(userRole);
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = getUserByName(username);
        userMapper.deleteByPrimaryKey(user.getId());
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updateUserLastLoginTimeById(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUserLockInfo(UserLockInfo userLockInfo, boolean isLock) {
        if (isLock){
            // if true
            userLockInfo.setExecuteLockType(LOCK);
        }else {
            userLockInfo.setExecuteLockType(UNLOCK);
        }
        userLockInfoMapper.updateByPrimaryKey(userLockInfo);
    }

    @Override
    public void updateUserPassword(Long userId, String newPassword) {
        User user = getUserById(userId);
        if (user!=null){
            PasswordSaltUserBO passwordSaltUserBO = PasswordHelper.encryptPasswordAndSetSalt(newPassword);
            user.setPassword(passwordSaltUserBO.getNewHashPassword());
            user.setSalt(passwordSaltUserBO.getNewHashSalt());
        }

        userMapper.updateByPrimaryKey(user);

    }

    @Override
    public List<User> listAuthUser() {
        UserExample userExample = new UserExample();
        return userMapper.selectByExample(userExample);
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByName(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> user = userMapper.selectByExample(userExample);
        if (user!=null && user.size()!=0){
            return user.get(0);
        }
        logger.debug("can not find the user who name " + username + ".");
        return null;
    }

    @Override
    public ActiveUserDTO getActiveUserByName(String username) {
        return null;
    }


    @Override
    public ActiveUserDTO getActiveUserById(Long userId) {
        return null;
    }

    @Override
    public void saveUserLockInfo(UserLockInfo userLockInfo) {
        userLockInfoMapper.insertSelective(userLockInfo);

    }


    @Override
    public void deleteUserLockInfoById(Long id) {
        userLockInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUserLockInfo(UserLockInfo userLockInfo) {
        userLockInfoMapper.updateByPrimaryKey(userLockInfo);
    }


    @Override
    public UserLockInfo getUerLockInfoById(Long id) {
        return userLockInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserLockInfo> listUserLockInfoByUsername(String username) {
        Long userId = getUserByName(username).getId();
        UserLockInfoExample userLockInfoExample = new UserLockInfoExample();
        UserLockInfoExample.Criteria criteria = userLockInfoExample.createCriteria();
        criteria.andTargetUserIdEqualTo(userId);
        return userLockInfoMapper.selectByExample(userLockInfoExample);
    }

    @Override
    public List<UserLockInfo> listUserLockInfoByUserId(Long id) {
        UserLockInfoExample userLockInfoExample = new UserLockInfoExample();
        UserLockInfoExample.Criteria criteria = userLockInfoExample.createCriteria();
        criteria.andTargetUserIdEqualTo(id);
        return userLockInfoMapper.selectByExample(userLockInfoExample);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {

        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userInfo.getUserId());
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
        if (userInfoList!=null && userInfoList.size()!=0){
            updateUserInfo(userInfo);
        }else {
            userInfoMapper.insertSelective(userInfo);
        }
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userInfo.getUserId());
        userInfoMapper.updateByExampleSelective(userInfo,userInfoExample);

    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {

        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(example);
        if (userInfoList!=null && userInfoList.size()!=0){
            return userInfoList.get(0);
        }else {
            return null;
        }
    }
}

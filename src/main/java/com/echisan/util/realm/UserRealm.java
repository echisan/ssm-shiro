package com.echisan.util.realm;

import com.echisan.model.po.Role;
import com.echisan.model.po.User;
import com.echisan.service.UserRolePermissionService;
import com.echisan.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author E73AN
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);


    @Autowired
    private UserService userService;

    @Autowired
    private UserRolePermissionService userRolePermissionService;



    @Override
    public String getName() {
        return "userRealm";
    }

    /**
     * 对用户授权
     *
     * @param principalCollection 凭证集合
     * @return 一个用户的系列权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

//         获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByName(username);
        Role role = userRolePermissionService.getUserRoleByUserId(user.getId());
        Set<String> roleSet = new HashSet<String>(Collections.singletonList(role.getCodeName()));

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        return info;
    }

    /**
     * 验证用户
     *
     * @param authenticationToken 用户的登陆口令
     * @return 验证成功的用户信息
     * @throws AuthenticationException 当用户帐号或密码不正确是抛出相应的错误
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByName(username);
        if (user!=null){
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        }
        logger.info(username+" is not exist");
        return null;
    }
}

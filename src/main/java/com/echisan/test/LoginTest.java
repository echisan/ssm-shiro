package com.echisan.test;

import com.echisan.model.bo.PasswordSaltUserBO;
import com.echisan.model.bo.RegisterUserBO;
import com.echisan.model.po.User;
import com.echisan.service.UserService;
import com.echisan.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author E73AN
 */
public class LoginTest extends BaseTest {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserService userService;

    @Before
    public void init(){
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void loginTest(){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("echisan","echisan");

        try {
            subject.login(token);

            subject.checkRole(UserService.SUPER_ADMIN);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addUser(){
        User user = new User();
        user.setUsername("inory");
        PasswordSaltUserBO registerUserBO = PasswordHelper.encryptPasswordAndSetSalt("inory");
        user.setPassword(registerUserBO.getNewHashPassword());
        user.setSalt(registerUserBO.getNewHashSalt());
        userService.saveDefaultUser(user);
    }

    @Test
    public void updateUserLastLoginTime(){
        userService.updateUserLastLoginTimeById(23L);
    }

}

package com.echisan.test;

import com.echisan.dao.UserMapper;
import com.echisan.model.bo.RegisterUserBO;
import com.echisan.model.po.User;
import com.echisan.service.GoodsService;
import com.echisan.service.UserService;
import com.echisan.util.PasswordHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author E73AN
 */
//@Transactional
//@Rollback
public class DaoTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(DaoTest.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Test
    public void test() {
        RegisterUserBO registerUserBO = new RegisterUserBO("admin","admin");
        PasswordHelper.encryptPasswordAndSetSalt(registerUserBO);
        User user = new User();
        try {
            BeanUtils.copyProperties(user, registerUserBO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userMapper.insert(user);
    }

    @Test
    public void getAllUser(){

        List<User> users = userService.listAuthUser();
        for (User u :
                users) {
            logger.info(String.valueOf(u.getId()));
            logger.info(String.valueOf(u.getId().getClass()));
        }

//        User user = userService.getUserById();
//        logger.info(user.toString());
    }

    @Test
    public void getUserById(){
        User user = userService.listAuthUser().get(0);
        Assert.isTrue(user.getId().equals(17L));
        User user1 = userService.getUserById(user.getId());
        logger.info(user1.toString());
    }

    @Test
    public void updateUser(){
        userService.updateUserPassword(17L,"gaygui");
    }

    @Test
    public void updateUserLastLoginTime(){
        userService.updateUserLastLoginTimeById(17L);
    }

    @Test
    public void addUser(){
        User user = new User();
        RegisterUserBO registerUserBO = new RegisterUserBO("admin","admin");
        PasswordHelper.encryptPasswordAndSetSalt(registerUserBO);
        user.setUsername(registerUserBO.getUsername());
        user.setSalt(registerUserBO.getSalt());
        user.setPassword(registerUserBO.getPassword());
        userService.saveUser(user, UserService.USER_TYPE_NORMAL_USER);
    }

    @Test
    public void getUserByUsername(){
        User user = userService.getUserByName("admin");
        logger.info(user.toString());
    }

    @Test
    public void addUserWithRole(){

        User user = new User();
        RegisterUserBO registerUserBO = new RegisterUserBO("echisan","echisan");
        PasswordHelper.encryptPasswordAndSetSalt(registerUserBO);
        user.setUsername(registerUserBO.getUsername());
        user.setSalt(registerUserBO.getSalt());
        user.setPassword(registerUserBO.getPassword());
        userService.saveUser(user, UserService.USER_TYPE_NORMAL_USER);
    }

    @Test
    public void goodsServiceTest(){

    }
}

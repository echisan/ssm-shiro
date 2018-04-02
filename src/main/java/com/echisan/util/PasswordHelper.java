package com.echisan.util;

import com.echisan.model.bo.PasswordSaltUserBO;
import com.echisan.model.bo.RegisterUserBO;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author E73AN
 */
public class PasswordHelper {

    private static final Logger logger = LoggerFactory.getLogger(PasswordHelper.class);

    private static final int HASH_PASSWORD_ITERATIONS = 2;

    public static void encryptPasswordAndSetSalt(RegisterUserBO registerUserBO){
        String salt = createRandomSalt();
        Md5Hash md5Hash = new Md5Hash(registerUserBO.getPassword(),salt,HASH_PASSWORD_ITERATIONS);
        registerUserBO.setSalt(salt);
        registerUserBO.setPassword(md5Hash.toHex());
        logger.info("random salt success and salt is:"+salt);
        logger.info("encrypt success and hash password is:"+md5Hash.toHex());
    }

    public static PasswordSaltUserBO encryptPasswordAndSetSalt(String newPassword){
        String salt = createRandomSalt();
        PasswordSaltUserBO passwordSaltUserBO = new PasswordSaltUserBO();
        passwordSaltUserBO.setNewHashSalt(salt);
        passwordSaltUserBO.setNewHashPassword(getEncryptPassword(newPassword,salt));
        return passwordSaltUserBO;
    }

    /**
     *
     * @param currentPassword 当前登录的用户的密码
     * @param salt 从数据库获取到的当前用户的密码的盐
     * @param targetPassword 从数据库获取到的已加密的密码
     * @return 如果密码相同返回true 反之
     */
    public static boolean isPasswordEquals(String currentPassword,String salt,String targetPassword){
        String currentHashPassword = getEncryptPassword(currentPassword,salt);
        boolean isEquals = currentHashPassword.equalsIgnoreCase(targetPassword);
        logger.info("current user hash password:"+currentHashPassword);
        logger.info("target user hash password:"+targetPassword);
        logger.info("is password equals = "+ isEquals);
        return isEquals;
    }

    private static String getEncryptPassword(String password, String salt){
        Md5Hash md5Hash = new Md5Hash(password,salt,HASH_PASSWORD_ITERATIONS);
        return md5Hash.toHex();
    }

    private static String createRandomSalt(){
        RandomNumberGenerator generator = new SecureRandomNumberGenerator();
        return generator.nextBytes().toHex();
    }

}


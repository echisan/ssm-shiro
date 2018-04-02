package com.echisan.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author E73AN
 */
public class LoginFormBO {


    @NotEmpty(message = "用户名不能为空！")
    private String username;
    @NotEmpty(message = "密码不能为空！")
    private String password;
    @NotEmpty(message = "验证码不能为空！")
    private String verifyCode;
    private Integer rememberMe;

    public LoginFormBO() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Integer rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginFormBO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}

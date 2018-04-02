package com.echisan.model.bo;

/**
 * @author E73AN
 */
public class RegisterFormBO {

    private String username;
    private String password;
    private String rePassword;
    private String verifyCode;

    public RegisterFormBO() {
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    @Override
    public String toString() {
        return "RegisterFormBO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}

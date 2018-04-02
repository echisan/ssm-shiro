package com.echisan.model.bo;


import com.echisan.model.po.User;

/**
 * @author E73AN
 */
public class RegisterUserBO extends User {

    private String username;
    private String password;
    private String salt;

    public RegisterUserBO() {
    }

    public RegisterUserBO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegisterUserBO(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "RegisterUserBO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}

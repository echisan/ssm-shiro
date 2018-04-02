package com.echisan.model.dto;

import com.echisan.model.po.UserAddress;

import java.util.List;

/**
 * 用于获取并更新的用户的传输对象
 * @author E73AN
 */
public class UpdateUserDTO{

    private Long id;
    private String username;
    private String password;
    private String salt;
    private String face;
    private List<UserAddress> userAddresses;

    public UpdateUserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    @Override
    public String toString() {
        return "UpdateUserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", face='" + face + '\'' +
                ", userAddresses=" + userAddresses +
                '}';
    }
}

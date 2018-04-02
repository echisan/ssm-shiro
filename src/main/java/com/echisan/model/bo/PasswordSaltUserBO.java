package com.echisan.model.bo;

/**
 * @author E73AN
 */
public class PasswordSaltUserBO {

    private String newHashPassword;
    private String newHashSalt;

    public PasswordSaltUserBO() {
    }

    public PasswordSaltUserBO(String newHashPassword, String newHashSalt) {
        this.newHashPassword = newHashPassword;
        this.newHashSalt = newHashSalt;
    }

    public String getNewHashPassword() {
        return newHashPassword;
    }

    public void setNewHashPassword(String newHashPassword) {
        this.newHashPassword = newHashPassword;
    }

    public String getNewHashSalt() {
        return newHashSalt;
    }

    public void setNewHashSalt(String newHashSalt) {
        this.newHashSalt = newHashSalt;
    }

    @Override
    public String toString() {
        return "PasswordSaltUserBO{" +
                "newHashPassword='" + newHashPassword + '\'' +
                ", newHashSalt='" + newHashSalt + '\'' +
                '}';
    }
}

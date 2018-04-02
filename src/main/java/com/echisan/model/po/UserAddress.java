package com.echisan.model.po;

public class UserAddress {
    private Long id;

    private String address;

    private Byte isDefaultAddress;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getIsDefaultAddress() {
        return isDefaultAddress;
    }

    public void setIsDefaultAddress(Byte isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
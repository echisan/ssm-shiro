package com.echisan.service;

import com.echisan.model.po.UserAddress;

import java.util.List;

/**
 * @author E73AN
 */
public interface UserAddressService {

    /**
     * 添加用户地址信息
     * @param userAddress 用户的地址的po类
     */
    void saveUserAddress(UserAddress userAddress);

    /**
     * 删除地址
     * @param addressId 地址id
     */
    void deleteUserAddressById(Long addressId);

    /**
     * 更改对应id的地址信息
     * @param userAddress 地址
     */
    void updateUserAddress(UserAddress userAddress);

    /**
     * 获取用户的地址列表
     * @param userId 用户的id
     * @return 用户的地址列表
     */
    List<UserAddress> listUserAddressesById(Long userId);

    /**
     * 获取用户默认地址
     * @param userId 用户id
     * @return 用户地址实体
     */
    UserAddress getUserDefaultAddressById(Long userId);

}
